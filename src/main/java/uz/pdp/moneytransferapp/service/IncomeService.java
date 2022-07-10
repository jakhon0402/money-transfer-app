package uz.pdp.moneytransferapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.moneytransferapp.entity.Card;
import uz.pdp.moneytransferapp.entity.Income;
import uz.pdp.moneytransferapp.payload.ApiResponse;
import uz.pdp.moneytransferapp.payload.CardDto;
import uz.pdp.moneytransferapp.repository.CardRepo;
import uz.pdp.moneytransferapp.repository.IncomeRepo;
import uz.pdp.moneytransferapp.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class IncomeService {
    @Autowired
    IncomeRepo incomeRepo;

    @Autowired
    CardRepo cardRepo;

    @Autowired
    JwtProvider jwtProvider;

    public List<Income> getIncomes(CardDto cardDto,HttpServletRequest request){
        String username = getUsername(request);
        if(!cardRepo.existsByUsernameAndNumber(username,cardDto.getNumber())){
            return null;
        }
        Card card = cardRepo.findByNumber(cardDto.getNumber());
        List<Income> incomeList = incomeRepo.findByToCard(card);
        return incomeList;
    }


    private String getUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token=token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        return username;
    }
}
