package duong.thuy.parking.controller;

import duong.thuy.parking.request.LoginRequest;
import duong.thuy.parking.request.RegisterRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.services.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsService;

    @PostMapping("register")
    public ResponseEntity<ResponseData> register(@Valid @RequestBody RegisterRequest request , BindingResult bindingResult){
        return credentialsService.register(request);
    }

    @PostMapping("login")
    public ResponseEntity<ResponseData> login(@Valid @RequestBody LoginRequest request , BindingResult bindingResult){
        return credentialsService.login(request);
    }

}
