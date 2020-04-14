package duong.thuy.parking.repository;

import duong.thuy.parking.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking,Long> {
}
