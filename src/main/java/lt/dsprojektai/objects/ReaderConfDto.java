package lt.dsprojektai.objects;

import lombok.Data;

@Data
public class ReaderConfDto {
    private String ip;
    private int power;
    private int timeBetweenInventories;
    private int timeBetweenAntennas;
    private int antennaReadTime;
    private String antennasToUse;
    private String session;
    private String q;
    private String status = "none";

    @Override
    public String toString() {
        return "";
    }
}
