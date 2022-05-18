package bobko.donatservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import bobko.donatservice.entities.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findFirst10ByOrderByIdDesc();
}
