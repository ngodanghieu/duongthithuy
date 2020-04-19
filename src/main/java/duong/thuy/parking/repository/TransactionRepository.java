package duong.thuy.parking.repository;

import duong.thuy.parking.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findAllByParkingIdAndStatus(int parking, String status);

    List<Transactions> findAllByStatus(String status);

    List<Transactions> findAllById(int  transactionId);
}
