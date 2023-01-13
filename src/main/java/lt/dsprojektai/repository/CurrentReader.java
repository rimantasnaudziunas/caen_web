package lt.dsprojektai.repository;

import com.caen.RFIDLibrary.CAENRFIDReader;
import lt.dsprojektai.exceptions.ReaderException;
import lt.dsprojektai.objects.Reader;
import org.springframework.stereotype.Repository;

@Repository
public class CurrentReader {
    private Reader reader;

    public void setReader (String ip){
        reader = new Reader();
        reader.setIp(ip);
    }

    public String getIp() {return reader.getIp();}
    public CAENRFIDReader getCAENReader() throws ReaderException {
        if (reader == null) {
            System.out.println("klaida CurrentReader.getIp()");
            throw new ReaderException("Problem with the RFID reader. Reader is not set.");
        }
        return reader.getReader();}

    @Override
    public String toString() {
        return reader.toString();
    }
}
