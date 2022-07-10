package uz.pdp.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.moneytransferapp.entity.Card;
import uz.pdp.moneytransferapp.entity.Outcome;

import java.util.List;

public interface OutcomeRepo extends JpaRepository<Outcome,Integer> {
    List<Outcome> findByFromCard(Card cardNumber);
}
