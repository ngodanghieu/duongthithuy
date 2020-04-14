package duong.thuy.parking.repository;

import duong.thuy.parking.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {
}
