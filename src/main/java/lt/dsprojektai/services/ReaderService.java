package lt.dsprojektai.services;

import com.caen.RFIDLibrary.*;
import lt.dsprojektai.exceptions.ReaderException;
import lt.dsprojektai.repository.CurrentReader;
import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Hex;

import static com.caen.RFIDLibrary.CAENRFIDPort.CAENRFID_TCP;
import static java.lang.Thread.sleep;

@Service
public class ReaderService {
    @Autowired
    private CurrentReader currentReader;

    private CAENRFIDLogicalSource source;
    private final short memBank = 1;
    private final short address = 4;
    private final short length = 12;
    private static int writeRepeat = 0;

    public void createReader(String ip){
        currentReader.setReader(ip);
    }

    public void connectToReader() throws ReaderException {
        try {
            currentReader.getCAENReader().Connect(CAENRFID_TCP, currentReader.getIp());
            this.source = currentReader.getCAENReader().GetSource("Source_0");
        } catch (CAENRFIDException e) {
            e.printStackTrace();
            throw new ReaderException("Problem with the RFID reader. Could not connect.");
        }
    }

    public void setReaderPower(int power) throws ReaderException {
        connectToReader();
        try {
            currentReader.getCAENReader().SetPower(power);
        } catch (CAENRFIDException e) {
            e.printStackTrace();
            throw new ReaderException("Problem with the RFID reader. Could not connect to reader to set power.");
        }finally {
            disconnectReader();
        }
    }

    public void disconnectReader() {
        try {
            currentReader.getCAENReader().Disconnect();
        } catch (CAENRFIDException | ReaderException e) {
            e.printStackTrace();
        }
    }

    public CAENRFIDTag[] readTags() throws CAENRFIDException {
        CAENRFIDTag[] tags = source.InventoryTag();
        return tags;
    }

    public void writeTag(String epc) throws ReaderException {
        connectToReader();
        String writeLoad = epc;

        CAENRFIDTag[] tags = new CAENRFIDTag[0];
        try {
            tags = readTags();
        } catch (CAENRFIDException e) {
            e.printStackTrace();
        }

        if ((tags == null) || (tags.length > 1)){
            disconnectReader();
            throw new ReaderException("too many or not enough tags");
        }

        writeToTag(tags[0], writeLoad);

        disconnectReader();
    }

    public CurrentReader getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(CurrentReader currentReader) {
        this.currentReader = currentReader;
    }

    /****************************************************************/

    private void writeToTag(CAENRFIDTag t, String writeLoad) throws ReaderException {
        writeRepeat = 0;
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        byte[] data = new byte[0];
        try {
            data = Hex.decodeHex(writeLoad.toCharArray());
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        writeProcedure(t, data, writeLoad);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void writeProcedure(CAENRFIDTag t, byte[] data, String writeLoad) throws ReaderException {
        try {
            source.WriteTagData_EPC_C1G2(t, memBank, address, length, data);
            System.out.println("Tag write success: " + writeLoad + "->" + getString(t.GetId()));
        } catch (CAENRFIDException caenrfidException) {
            if (writeRepeat < 20) {
                System.out.println("RETRY: " + writeRepeat);
                writeRepeat++;
                writeProcedure(t, data, writeLoad);
            } else {
                writeRepeat = 0;
                System.out.println("Unable to write to tag: " + getString(t.GetId()));
                throw new ReaderException("Was unable to write RFID tag.");
            }
        }
    }

    public static String getString(byte[] DataToRead) {
        String hexTag;
        String hexTagWord;
        hexTag = "";
        for (byte b : DataToRead) {
            hexTagWord = Integer.toHexString((int) (b & 0xFF));
            if (hexTagWord.length() < 2) {
                hexTagWord = "0" + hexTagWord;
            }
            hexTag += hexTagWord;
        }
        return hexTag;
    }
}
