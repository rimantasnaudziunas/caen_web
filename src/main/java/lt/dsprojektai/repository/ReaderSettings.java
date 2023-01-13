package lt.dsprojektai.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class ReaderSettings {
    private String ip;
    private int power;
    private int timeBetweenInventories;
    private int timeBetweenAntennas;
    private int antennaReadTime;
    private String antennasToUse;
    private String session;
    private String q;
}
