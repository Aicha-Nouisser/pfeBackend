package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.DTO.CountResult;
import tn.esprit.spring.entity.Transaction;


@Repository

public interface TransactionRepository extends JpaRepository< Transaction, Long>{ 
	///oordre deesc
@Query(value= "select * from gpg_transaction order by datetransaction desc", nativeQuery = true)
public List <Transaction> getAllTransactionByDateTransaction();


@Query(value = "SELECT new tn.esprit.spring.DTO.CountResult(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Transaction), result) " 
        + "FROM Transaction GROUP BY result")
public List<CountResult> getPercentageGroupByResult();

}
