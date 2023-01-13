package lt.dsprojektai.controller;

import com.caen.RFIDLibrary.CAENRFIDException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lt.dsprojektai.exceptions.ReaderException;
import lt.dsprojektai.objects.ReaderConfDto;
import lt.dsprojektai.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RestRFIDReaderController {
    @Autowired
    private ReaderService readerService;

    @PostMapping(value = "/setReaderIP", consumes = "application/json", produces = "application/json")
    public String setReaderIP(@RequestBody String string) {
        System.out.println(string);
        //readerService.createReader(string);
        return "pavyko";
    }

    @GetMapping(value = "/getReaderIP")
    public String getReaderIP()  {
        try {
            readerService.getCurrentReader().getCAENReader();
        } catch (ReaderException e) {
            return "NaN";
        }
        System.out.println("getReaderIP: " + readerService.getCurrentReader().getIp());
        return readerService.getCurrentReader().getIp();
    }

    @GetMapping(value = "/getReaderSettings")
    public ReaderConfDto getReaderSettings() {
        ReaderConfDto readerConfDto = new ReaderConfDto();
        try {
            readerService.connectToReader();
            readerConfDto.setIp(readerService.getCurrentReader().getIp());
            readerConfDto.setPower(readerService.getCurrentReader().getCAENReader().GetPower());
            readerService.disconnectReader();
        } catch (CAENRFIDException e) {
            e.printStackTrace();
        } catch (ReaderException e) {
            e.printStackTrace();
        } finally {
            readerService.disconnectReader();
            System.out.println("disconected get");
        }
        return readerConfDto;
    }

    @PostMapping(value = "/setReaderSettings", consumes = "application/json", produces = "application/json")
    public String setReaderSettings(@RequestBody String string) throws JsonProcessingException, CAENRFIDException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(string);
        System.out.println("call setReaderPower");
        try {
            readerService.setReaderPower(node.get("power").asInt());
        } catch (ReaderException e) {
            e.printStackTrace();
            return e.geteCause();
        }
        return "pavyko";
    }
}
