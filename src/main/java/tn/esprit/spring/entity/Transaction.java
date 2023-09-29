package tn.esprit.spring.entity;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table(name = "gpg_transaction")
public class Transaction {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idTransaction;
	   
	   
	   @Column
	    private String surname;
	   
	   @Column
	    private String givenName;
	   
	   @Column
	    private Integer montant;
	   
	   @Column
	    private String devise;

	    @Temporal(TemporalType.DATE)
	    private Date datetransaction;
	    @Column
	    private String result;
	   
	  //////relation
		@JsonIgnore

	   @ManyToOne
	   
	   private Site site;
	   
	  
	   
}
