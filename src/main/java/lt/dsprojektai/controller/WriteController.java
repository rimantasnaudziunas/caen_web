package lt.dsprojektai.controller;

import lt.dsprojektai.objects.EpcFile;
import lt.dsprojektai.services.EpcToWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WriteController {

    @Autowired
    EpcToWriteService epcToWriteService;

    @RequestMapping(value = "/addepcs", method = RequestMethod.GET)
    public String addEpcsGET(@ModelAttribute("epcFile") EpcFile epcFile, HttpServletRequest request) {
        return "addEpcFile";
    }

    @RequestMapping("/write")
    public String writeEPC(@RequestParam("epc") String epc) {
        String writeResult = epcToWriteService.writeEpc(epc);
        epcToWriteService.removeEpc(epc);
        //TODO return errors
        return "redirect:addepcs";
    }

    @RequestMapping(value = "/addepcs", method = RequestMethod.POST)
    public String addEpcsPOST(@ModelAttribute("epcFile") EpcFile epcFile, HttpServletRequest request) {
        System.out.println("epc file uploaded");
        epcToWriteService.processEpcFile(epcFile);
        System.out.println(epcToWriteService.getEpcsToWrite());
        return "redirect:addepcs";
    }


}
