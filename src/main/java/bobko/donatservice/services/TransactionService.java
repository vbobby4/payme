package bobko.donatservice.services;

import bobko.donatservice.entities.Transaction;
import bobko.donatservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bobko.donatservice.entities.FrontTransaction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    Map<String, String> patterns = new HashMap<String, String>(){{
        put("privat", "Приват24: ");
        put("mono", "Від: ");
    }};
    @Autowired
    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void save(Transaction transaction){
        repository.save(transaction);
    }

    public Transaction getLastTransaction() { return repository.getOne(repository.count()); }
    public FrontTransaction getFrontTransactionFromDefault(Transaction transaction){
        if (transaction.getComment().isEmpty())
            transaction.setComment("Быть добру!");
        FrontTransaction frontTransaction = new FrontTransaction(transaction.getTransID(),transaction.getComment(), BigDecimal.valueOf((float) transaction.getAmount() / 100).floatValue(),BigDecimal.valueOf((float) transaction.getBalance() / 100).floatValue());
        String description = transaction.getDescription();
        if (description.contains(patterns.get("privat")))
            frontTransaction.setSender(description.substring(patterns.get("privat").length()));
        else if (description.contains(patterns.get("mono")))
            frontTransaction.setSender(description.substring(patterns.get("mono").length()));
        else
            frontTransaction.setSender("Anonymous");
        return frontTransaction;
    }
}
