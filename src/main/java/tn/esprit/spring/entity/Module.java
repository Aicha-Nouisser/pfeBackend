package tn.esprit.spring.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;

import lombok.Data;

@Data
@Entity
@Table(name = "gpg_module")
public class Module {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idModule;
    
    @Column(name = "codeModule", length = 11)
    private String codeModule;
    
    @Column(name = "nomModule", length = 100)
    private String nomModule;
    //@ManyToMany(cascade = {
      //      CascadeType.PERSIST,
        //    CascadeType.MERGE
    //})
    //@JoinTable(name = "module_role",
      ///      joinColumns = @JoinColumn(name = "module_id"),
         //   inverseJoinColumns = @JoinColumn(name = "role_id")
    //)
    //private Set<Role> roles = new HashSet<>();


}
    		
    		
    