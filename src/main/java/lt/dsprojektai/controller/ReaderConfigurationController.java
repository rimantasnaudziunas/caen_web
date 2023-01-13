package lt.dsprojektai.controller;

import lt.dsprojektai.objects.ReaderConfDto;
import lt.dsprojektai.services.ReaderConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReaderConfigurationController {

    @Autowired
    ReaderConfigurationService readerConfigurationService;

    @RequestMapping(value = "config")
    public String getReaderConfigurationGET(Model model, @ModelAttribute("ReaderConfDto") ReaderConfDto readerConfDto){
        System.out.println(readerConfDto.toString());
        return "readerConfiguration";
    }
}
