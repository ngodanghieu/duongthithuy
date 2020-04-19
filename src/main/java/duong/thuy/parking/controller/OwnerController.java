package duong.thuy.parking.controller;

import duong.thuy.parking.request.CreateOwnerRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.services.OwnerService;
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
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("create/owner")
    public ResponseEntity<ResponseData> createOwner(@Valid @RequestBody CreateOwnerRequest request , BindingResult bindingResult){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return ownerService.createOwner(request,Integer.parseInt(userId));
    }
}
