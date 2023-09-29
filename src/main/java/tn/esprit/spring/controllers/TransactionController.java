package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import tn.esprit.spring.DTO.CountResult;
import tn.esprit.spring.entity.Site;
import tn.esprit.spring.entity.Transaction;
import tn.esprit.spring.service.SiteService;
import tn.esprit.spring.service.TransactionService;
import tn.esprit.spring.repository.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class TransactionController {
	@Autowired 
	  TransactionService ss;
	
	@Autowired 
	SiteService siteService;
	@Autowired 
	  TransactionRepository tt;
	@PostMapping("/addTransaction")
	@ResponseBody
	public Transaction addTransactions(@RequestBody Transaction transaction) {
	    return ss.save(transaction);
	}
	
	@PutMapping("/addTransactiontoSite/{idTransaction}/{idSite}")
	@ResponseBody
	public void AffecterTransactionSite(@PathVariable ("idTransaction") Long idTransaction ,@PathVariable("idSite") Long idSite)
	{
	 ss.AffecterTransactionSite(idTransaction, idSite);
	}
	
	
	@DeleteMapping("/deleteTransaction/{idTransaction}")
	@ResponseBody
	
	public void deleteTransactions(@PathVariable ("idTransaction") Long idTransaction) {
		
		ss.deleteTransactions(idTransaction);
	}
	
	
	@GetMapping("/Transaction")
	@ResponseBody
	public List<Transaction> getTransactions(){
		return tt.getAllTransactionByDateTransaction();
	}
	
	@GetMapping("/Transaction/pourcentage")
	@ResponseBody

	public List<CountResult> getPercentageGroupByResult()
	{
		return ss.getPercentageGroupByResult();
	}
	
	
	@GetMapping("/highestRevenueSite")
	public Site getSiteWithHighestRevenue() {
	    return siteService.getSiteWithHighestRevenue();
	}
	
	
	@GetMapping("/getallTransaction")
	@ResponseBody

	public List<Transaction> getTransactionss(){
		return ss.getTransactionss();


}
	
	@PutMapping("/modify-Transaction/{id}")
	@ResponseBody
	public Transaction ModifyTransaction(@PathVariable("id") Long idTransaction,@RequestBody Transaction t) {
		return ss.updateTransactionById(t, idTransaction);

		}

	@GetMapping("/getTransactionby/{id}")
	@ResponseBody
	public Transaction getTransactionsbyid(@PathVariable("id") Long idTransaction) {
	    return ss.findById(idTransaction);
	}
}

	




	