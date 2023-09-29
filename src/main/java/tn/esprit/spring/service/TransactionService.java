package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.DTO.CountResult;
import tn.esprit.spring.entity.Transaction;

public interface TransactionService {
public void AffecterTransactionSite(Long idTransaction,Long idSite) ;

public List<Transaction> getTransactions();
public Transaction save(Transaction transaction );
public List<CountResult> getPercentageGroupByResult();
public List<Transaction> getTransactionss();
void deleteTransactions(Long idTransaction);
Transaction updateTransactionById(Transaction o,Long idTransaction);
Transaction findById(Long idTransaction);


}

