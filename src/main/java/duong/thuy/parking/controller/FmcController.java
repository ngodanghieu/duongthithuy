package duong.thuy.parking.controller;

import duong.thuy.parking.request.FmcRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.services.FmcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class FmcController {

    @Autowired
    private FmcService fmcService;

    @PostMapping("save/token/firebase")
    public ResponseEntity<ResponseData> requestUpdateFCMToken(@RequestBody FmcRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return fmcService.requestUpdateFCMToken(request,Integer.parseInt(userId));
    }
}
