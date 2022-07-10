package uz.pdp.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.moneytransferapp.entity.Card;
import uz.pdp.moneytransferapp.entity.Income;
import uz.pdp.moneytransferapp.entity.Outcome;

import java.util.List;

public interface IncomeRepo extends JpaRepository<Income,Integer> {
    List<Income> findByToCard(Card cardNumber);
}
