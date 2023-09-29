package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

import tn.esprit.spring.entity.Site;
import tn.esprit.spring.entity.Transaction;
import tn.esprit.spring.service.SiteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class SiteController {


	@Autowired 
	SiteService siteService;


	@PostMapping("/addSite")
	@ResponseBody
	public Site  addSites(@RequestBody Site site) {
	    return siteService.save(site);
	}

	@DeleteMapping("/deleteSite/{id}")
	@ResponseBody
	public void deleteSites(@PathVariable ("id") Long id) {
		siteService.deleteSiteById(id);
	}
	
	@GetMapping("/getallSite")
	@ResponseBody

	public List<Site> getSites(){
		return siteService.getSites();


}
	

	@PutMapping("/modify-Site/{id}")
	@ResponseBody
	public Site ModifySite(@PathVariable("id") Long id,@RequestBody Site t) {
		return siteService.updateSiteById(t, id);

		}
	
	@GetMapping("/getSiteby/{id}")
	@ResponseBody
	public Site getSitesbyid(@PathVariable("id") Long id) {
	    return siteService.findById(id);
	}
}
