package duong.thuy.parking.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChangeTransactionStatusRequest {

    @JsonProperty("transactionId")
    private int transactionId;

    @JsonProperty("status")
    private Long status;
}
