package uz.pdp.moneytransferapp.service;

import uz.pdp.moneytransferapp.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.moneytransferapp.payload.ApiResponse;
import uz.pdp.moneytransferapp.payload.CardDto;
import uz.pdp.moneytransferapp.repository.CardRepo;
import uz.pdp.moneytransferapp.security.JwtProvider;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepo cardRepo;

    @Autowired
    JwtProvider jwtProvider;

    static final double INITIAL_AMOUNT = 50.0;
    static final long EXPIRE_TIME = 126_227_808_000L; // 4 years

    public List<Card> getCards(){return cardRepo.findAll();}

    public Card getCardById(Integer id){
        Optional<Card> optionalCard = cardRepo.findById(id);
        return optionalCard.orElse(null);
    }

    public ApiResponse addCard(CardDto cardDto,HttpServletRequest request){
        String username = getUsername(request);
        boolean exists = cardRepo.existsByNumber(cardDto.getNumber());
        if(exists){
            return new ApiResponse("Bunday numberlik card mavjud!",false);
        }
        Card card = new Card();
        card.setUsername(username);
        card.setNumber(cardDto.getNumber());
        card.setExpiredDate(new Date(System.currentTimeMillis()+EXPIRE_TIME));
        card.setBalance(INITIAL_AMOUNT);
        card.setActive(true);
        cardRepo.save(card);
        return new ApiResponse("Card muvaffaqiyatli qo'shildi!",true);

    }

    private String getUsername(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        token=token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);
        return username;
    }
}
