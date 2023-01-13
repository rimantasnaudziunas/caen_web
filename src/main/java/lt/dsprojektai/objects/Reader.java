package lt.dsprojektai.objects;

import com.caen.RFIDLibrary.CAENRFIDReader;

public class Reader {
    private CAENRFIDReader reader;
    private String ip;

    public Reader(){
        reader = new CAENRFIDReader();
    }

    public CAENRFIDReader getReader() {
        return reader;
    }

    public void setReader(CAENRFIDReader reader) {
        this.reader = reader;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString(){
        return "Reader{" +
                "reader='" + reader.toString() + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
