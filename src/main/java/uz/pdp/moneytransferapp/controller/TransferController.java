package uz.pdp.moneytransferapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.moneytransferapp.payload.ApiResponse;
import uz.pdp.moneytransferapp.payload.TransferDto;
import uz.pdp.moneytransferapp.service.TransferService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    @Autowired
    TransferService transferService;

    @PostMapping
    public ResponseEntity transferMoney(@RequestBody TransferDto transferDto, HttpServletRequest request){
        ApiResponse apiResponse = transferService.transferMoney(request, transferDto);
        return ResponseEntity.status(apiResponse.isStatus()?200:409).body(apiResponse.getMessage());
    }
}
