package uz.pdp.moneytransferapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {
    private String fromCard;
    private String toCard;
    private double amount;
}
