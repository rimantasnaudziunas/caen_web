package lt.dsprojektai.controller;

import com.caen.RFIDLibrary.CAENRFIDException;
import lt.dsprojektai.objects.ReaderConfDto;
import lt.dsprojektai.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @Autowired
    ReaderService readerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model, @ModelAttribute("ip") ReaderConfDto readerConfDto){
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String indexPagePOST(Model model, @ModelAttribute("ip") ReaderConfDto readerConfDto) throws CAENRFIDException {
        System.out.println("returned reader ip: " + readerConfDto.getIp());
        readerService.createReader(readerConfDto.getIp());
        if (readerConfDto.getIp().length() > 17) {
            return "error"; //TODO create and persist connection to reader
        }
        return "index";
    }
}
