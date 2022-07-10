package uz.pdp.moneytransferapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.moneytransferapp.entity.Income;
import uz.pdp.moneytransferapp.payload.CardDto;
import uz.pdp.moneytransferapp.service.IncomeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    @GetMapping
    public List<Income> getIncomes(HttpServletRequest request, @RequestBody CardDto cardDto){
        List<Income> incomeList = incomeService.getIncomes(cardDto, request);
        return incomeList;
    }
}
