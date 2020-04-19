package duong.thuy.parking.controller;

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
        return parkingService.createParking(request,Long.valueOf(userId));
    }

    @GetMapping("owner/get/all/parkings")
    public ResponseEntity<ResponseData> getAllParkingByOwner() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return parkingService.getAllParkingByOwner(Long.valueOf(userId));
    }

    @GetMapping("get/all/approved/parkings")
    public ResponseEntity<ResponseData> getAllParkingApproved() {
        return parkingService.getAllParkingApproved();
    }

    @GetMapping("calculate/amount/parking/{id}")
    public ResponseEntity<ResponseData> getStartAndPoint(@PathParam("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) auth.getPrincipal();
        return parkingService.getStartAndPoint(id,Long.valueOf(userId));
    }

    @GetMapping("get/parking/{parkingId}")
    public ResponseEntity<ResponseData> getDetailParking(@PathParam("parkingId") int parkingId) {
        return parkingService.getDetailParking(parkingId);
    }
}
