package uz.pdp.moneytransferapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.moneytransferapp.entity.Card;
import uz.pdp.moneytransferapp.entity.Income;
import uz.pdp.moneytransferapp.entity.Outcome;
import uz.pdp.moneytransferapp.payload.ApiResponse;
import uz.pdp.moneytransferapp.payload.TransferDto;
import uz.pdp.moneytransferapp.repository.CardRepo;
import uz.pdp.moneytransferapp.repository.IncomeRepo;
import uz.pdp.moneytransferapp.repository.OutcomeRepo;
import uz.pdp.moneytransferapp.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Service
public class TransferService {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    CardRepo cardRepo;

    @Autowired
    OutcomeRepo outcomeRepo;

    @Autowired
    IncomeRepo incomeRepo;

    static final double COMMISSION = 0.1;

    public ApiResponse transferMoney(HttpServletRequest request, TransferDto transferDto){
        String username = getUsername(request);

        if(!cardRepo.existsByUsernameAndNumber(username, transferDto.getFromCard())) {
            return new ApiResponse("Ushbu card usernamega tegishli emas!", false);
        }
        Card fromCard = cardRepo.findByNumber(transferDto.getFromCard());
        if(!fromCard.isActive()){return new ApiResponse("Ushbu card active emas!",false);}

        if(!cardRepo.existsByNumber(transferDto.getToCard())){
            return new ApiResponse("Jo'natilayotgan cardNumber xato kiritilgan.",false);
        }
        Card toCard = cardRepo.findByNumber(transferDto.getToCard());
        if(fromCard.getBalance() < transferDto.getAmount()+ transferDto.getAmount()*COMMISSION){
            return new ApiResponse("Mablag' yetarli emas!",false);
        }
        fromCard.setBalance(fromCard.getBalance() - transferDto.getAmount()- transferDto.getAmount()*COMMISSION);
        cardRepo.save(fromCard);

        toCard.setBalance(toCard.getBalance() + transferDto.getAmount());
        cardRepo.save(toCard);

        Outcome outcome = new Outcome();
        outcome.setFromCard(fromCard);
        outcome.setToCard(toCard);
        outcome.setAmount(transferDto.getAmount());
        outcome.setCommission_amount(transferDto.getAmount()*COMMISSION);
        outcome.setDate(new Date(System.currentTimeMillis()));
        outcomeRepo.save(outcome);

        Income income = new Income();
        income.setFromCard(fromCard);
        income.setToCard(toCard);
        income.setAmount(transferDto.getAmount());
        income.setDate(new Date(System.currentTimeMillis()));
        incomeRepo.save(income);
        return new ApiResponse("To'lov muvaffaqiyatli amalga oshirildi!",true);

    }

    private String getUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token=token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        return username;
    }
}
