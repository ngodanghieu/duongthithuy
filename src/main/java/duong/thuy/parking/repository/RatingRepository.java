package duong.thuy.parking.repository;

import duong.thuy.parking.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Ratings,Long> {
    List<Ratings> findAllByParkingId(int parkingId);
}
