package duong.thuy.parking.controller;

import duong.thuy.parking.request.CreateOwnerRequest;
import duong.thuy.parking.request.ParkingSpotRequest;
import duong.thuy.parking.response.ResponseData;
import duong.thuy.parking.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("user/share/parking")
    public ResponseEntity<ResponseData> createParking(@Valid @RequestBody ParkingSpotRequest request, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return parkingService.createParking(request,Integer.valueOf(userId));
    }

    @GetMapping("owner/get/all/parkings")
    public ResponseEntity<ResponseData> getAllParkingByOwner() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return parkingService.getAllParkingByOwner(Integer.valueOf(userId));
    }

    @GetMapping("get/all/approved/parkings")
    public ResponseEntity<ResponseData> getAllParkingApproved() {
        return parkingService.getAllParkingApproved();
    }

    @GetMapping("calculate/amount/parking/{id}")
    public ResponseEntity<ResponseData> getStartAndPoint(@PathParam("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return parkingService.getStartAndPoint(id,Integer.valueOf(userId));
    }
}