package uz.pdp.moneytransferapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.moneytransferapp.payload.ApiResponse;
import uz.pdp.moneytransferapp.payload.CardDto;
import uz.pdp.moneytransferapp.service.CardService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping
    public ResponseEntity addCard(HttpServletRequest request, @RequestBody CardDto cardDto){
        ApiResponse apiResponse = cardService.addCard(cardDto, request);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse.getMessage());
    }
}
