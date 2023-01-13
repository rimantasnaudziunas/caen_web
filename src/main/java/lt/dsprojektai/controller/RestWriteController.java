package lt.dsprojektai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.dsprojektai.services.EpcToWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RestWriteController {

    @Autowired
    EpcToWriteService epcToWriteService;

    private String writeResult;

    @PostMapping(value = "/writeEpc", consumes = "application/json", produces = "text/plain;charset=UTF-8")
    @ResponseStatus (code = HttpStatus.OK)
    public String writeEPC(@RequestBody String epcJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(epcJson);
        String epc = node.get("epc").asText();
        writeResult = epcToWriteService.writeEpc(epc);
        System.out.println(writeResult);
        if (writeResult.substring(0,9).equals("Sėkmingai")){
            epcToWriteService.removeEpc(epc);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, writeResult);
        }
        return writeResult;
    }
}
