package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.entity.Transaction;
import tn.esprit.spring.repository.SiteRepository;
import tn.esprit.spring.repository.TransactionRepository;
import tn.esprit.spring.DTO.CountResult;
import tn.esprit.spring.entity.*;
@Service

public class Transactionimpl  implements  TransactionService{
	@Autowired
	TransactionRepository repository;


	@Autowired
 SiteRepository ur;
	
	

	@Override 
	public void AffecterTransactionSite(Long idTransaction, Long idSite) {

		
		Site SiteEntity = ur.findById(idSite).get();
		Transaction TrasanctionEntity  = repository.findById(idTransaction).get();
		TrasanctionEntity.setSite(SiteEntity);
		repository.save(TrasanctionEntity);
	}	
	
	public List<Transaction> getTransactions(){
		return repository.getAllTransactionByDateTransaction();
	}
	
	public Transaction save(Transaction transaction ){
		return repository.saveAndFlush(transaction);
	}
	
	public List<CountResult> getPercentageGroupByResult()
	{
		return repository.getPercentageGroupByResult();
	}

	
	public List<Transaction> getTransactionss(){
		return repository.findAll();
	}

	@Override
	public void deleteTransactions(Long idTransaction) {
	repository.deleteById(idTransaction);		
	}

	@Override
	public Transaction updateTransactionById(Transaction o, Long idTransaction) {
		// TODO Auto-generated method stub
		Transaction cl= repository.findById(idTransaction).orElse(null);
		
		


cl.setDatetransaction(o.getDatetransaction());
cl.setDevise(o.getDevise());
cl.setGivenName(o.getGivenName());
cl.setSurname(o.getSurname());
cl.setMontant(o.getMontant());
cl.setResult(o.getResult());
cl.setDevise(o.getDevise());

return repository.save(cl);
	}
	@Override
	public Transaction findById(Long idTransaction) {
	    return repository.findById(idTransaction).orElse(null);
	}

	
	
}


