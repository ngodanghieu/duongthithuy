package duong.thuy.parking.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
@Validated
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("user/create/transaction")
    public ResponseEntity<ResponseData> createTransaction(@Valid @RequestBody CreateTransactionRequest request, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return transactionService.createTransaction(request,Integer.valueOf(userId));
    }
}
