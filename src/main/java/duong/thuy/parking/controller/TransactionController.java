package duong.thuy.parking.controller;

import duong.thuy.parking.request.ChangeTransactionStatusRequest;
import duong.thuy.parking.request.CreateTransactionRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.services.TransactionService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/")
@Validated
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("user/create/transaction")
    public ResponseEntity<ResponseData> createTransaction(@Valid @RequestBody CreateTransactionRequest request,
                                                          BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return transactionService.createTransaction(request,Integer.valueOf(userId));
    }
    @PostMapping("owner/get/transactions/parking/{parkingId}/state/{status}")
    public ResponseEntity<ResponseData> getListParkingRequestForHost(@PathParam("parkingId")int parkingId,
                                                                     @PathParam("status")String status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return transactionService.getListParkingRequestForHost(parkingId,status);
    }

    @PostMapping("user/get/all/transaction/{status}")
    public ResponseEntity<ResponseData> getListParkingRequestForUser(@PathParam("status")String status) {
        return transactionService.getListParkingRequestForUser(status);
    }

    @PatchMapping("/change/transaction")
    public ResponseEntity<ResponseData> updateStatusRequestParking( @Valid @RequestBody ChangeTransactionStatusRequest request,
                                                                   BindingResult bindingResult) {
        return transactionService.updateStatusRequestParking(request);
    }
}
