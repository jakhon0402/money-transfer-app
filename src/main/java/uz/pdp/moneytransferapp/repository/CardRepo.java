package uz.pdp.moneytransferapp.repository;

import uz.pdp.moneytransferapp.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card,Integer> {
    boolean existsByUsernameAndNumber(String username, String number);
    boolean existsByNumber(String number);
    Card findByNumber(String number);
}
