package uz.pdp.moneytransferapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.moneytransferapp.entity.Outcome;
import uz.pdp.moneytransferapp.payload.CardDto;
import uz.pdp.moneytransferapp.service.OutcomeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/outcome")
public class OutcomeController {
    @Autowired
    OutcomeService outcomeService;
    
    @GetMapping
    public List<Outcome> getOutcomes(HttpServletRequest request, @RequestBody CardDto cardDto){
        List<Outcome> outcomeList = outcomeService.getOutcomes(cardDto, request);
        return outcomeList;
    }
}
