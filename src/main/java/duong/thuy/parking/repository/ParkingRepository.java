package duong.thuy.parking.repository;

import duong.thuy.parking.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    List<Parking> findAllByLongitudeAndLatitude(String longitude, String latitude);

    List<Parking> findAllByOwnerId(int userId);

    List<Parking> findAllByStatus(String status);

    List<Parking> findById(int parkingId);
}
