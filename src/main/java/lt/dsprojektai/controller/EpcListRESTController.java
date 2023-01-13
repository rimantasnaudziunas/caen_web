package lt.dsprojektai.controller;

import lt.dsprojektai.objects.EpcToWrite;
import lt.dsprojektai.services.EpcToWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EpcListRESTController {
    @Autowired
    EpcToWriteService epcToWriteService;

    @RequestMapping("/getepclist")
    public List<EpcToWrite> getEpcList(){
        return epcToWriteService.getEpcsToWrite().getEpcToWriteList();
    }
}
