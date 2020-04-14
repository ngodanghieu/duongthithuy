package duong.thuy.parking.repository;

import duong.thuy.parking.entities.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Ratings,Long> {
}
