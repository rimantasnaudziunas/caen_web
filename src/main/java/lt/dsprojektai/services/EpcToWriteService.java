package lt.dsprojektai.services;

import lt.dsprojektai.exceptions.ReaderException;
import lt.dsprojektai.objects.EpcFile;
import lt.dsprojektai.objects.EpcToWrite;
import lt.dsprojektai.repository.EpcsToWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EpcToWriteService {
    @Autowired
    private EpcsToWrite epcsToWrite;

    @Autowired
    private ReaderService readerService;

    public void addEpcToList(EpcToWrite e) {
        epcsToWrite.getEpcToWriteList().add(e);
    }

    public void addEpcToList(String epc, String name) {
        epcsToWrite.getEpcToWriteList().add(new EpcToWrite(epc, name));
    }

    public EpcsToWrite getEpcsToWrite() {
        return epcsToWrite;
    }

    public void setEpcsToWrite(EpcsToWrite epcsToWrite) {
        this.epcsToWrite = epcsToWrite;
    }

    public void processEpcFile(EpcFile epcFile) {
        MultipartFile file = epcFile.getFile();
        String fullFile = null;
        EpcsToWrite newEpcList = new EpcsToWrite();
        try {
            fullFile = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = fullFile.split("\r\n");
        for (String line : lines) {
            newEpcList.getEpcToWriteList().add(new EpcToWrite(line.split(";")[0], line.split(";")[1]));
        }
        setEpcsToWrite(newEpcList);
    }

    public void removeEpc(String epc) {
        epcsToWrite.removeEpc(epc);
    }

    public String writeEpc(String epc) {
        try {
            readerService.writeTag(epc);
        } catch (ReaderException e) {
            System.out.println(e.geteCause());
            return e.geteCause() + ". Could not write tag: " + epc;
        }
        return "Sėkmingai įrašyta: " + epc;
    }
}
