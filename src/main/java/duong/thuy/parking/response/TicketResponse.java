package duong.thuy.parking.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TicketResponse {

    @JsonProperty("startTime")
    private String startTime;

    @JsonProperty("endTime")
    private String endTime;

    @JsonProperty("licence")
    private String licence;

    @JsonProperty("address")
    private String address;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("hostPhoneNumber")
    private String hostPhoneNumber;

    @JsonProperty("userPhoneNumber")
    private String userPhoneNumber;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("parkingId")
    private int parkingId;

    @JsonProperty("transactionId")
    private Long transactionId;

}
