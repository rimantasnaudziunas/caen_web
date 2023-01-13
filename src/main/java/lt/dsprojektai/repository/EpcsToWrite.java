package lt.dsprojektai.repository;

import lt.dsprojektai.objects.EpcToWrite;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EpcsToWrite {
    private List<EpcToWrite> epcToWriteList = new ArrayList<>();

    public List<EpcToWrite> getEpcToWriteList() {
        return epcToWriteList;
    }

    public void setEpcToWriteList(List<EpcToWrite> epcToWriteList) {
        this.epcToWriteList = epcToWriteList;
    }

    @Override
    public String toString() {
        return "EpcsToWrite{" +
                "epcToWriteList=" + epcToWriteList +
                '}';
    }

    public void removeEpc(String epc) {
        for (EpcToWrite etw : epcToWriteList) {
            if (etw.getEpc().equals(epc)){
                epcToWriteList.remove(etw);
                break;
            }
        }
    }
}
