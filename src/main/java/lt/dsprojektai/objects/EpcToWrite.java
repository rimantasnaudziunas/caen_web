package lt.dsprojektai.objects;

public class EpcToWrite {
    private String epc;
    private String name;

    public EpcToWrite(String epc, String name) {
        this.epc = epc;
        this.name = name;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EpcToWrite{" +
                "epc='" + epc + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
