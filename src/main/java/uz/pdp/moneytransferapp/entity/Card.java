package uz.pdp.moneytransferapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String number;

    @Column
    private double balance;

    @Column(nullable = false)
    private Date expiredDate;

    @Column
    private boolean active;

}
