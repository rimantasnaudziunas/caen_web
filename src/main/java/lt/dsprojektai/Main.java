package lt.dsprojektai;

import com.caen.RFIDLibrary.CAENRFIDException;
import com.caen.RFIDLibrary.CAENRFIDPort;
import com.caen.RFIDLibrary.CAENRFIDReader;

public class Main {
    public static void main(String[] args) {
        CAENRFIDReader reader;
        reader = new CAENRFIDReader();
        try {
            reader.Connect(CAENRFIDPort.CAENRFID_TCP, "192.168.1.2");
            reader.Disconnect();
        } catch (CAENRFIDException e) {
            e.printStackTrace();
        }
    }
}
