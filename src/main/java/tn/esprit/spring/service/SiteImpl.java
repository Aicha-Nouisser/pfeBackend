package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Site;
import tn.esprit.spring.entity.Transaction;
import tn.esprit.spring.repository.SiteRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service

public class SiteImpl  implements SiteService{
	
	  @Autowired
	    private SiteRepository siteRepository;

	    public Site getSiteWithHighestRevenue() {
	        Sort sortByRevenueDesc = Sort.by(Sort.Direction.DESC, "revenue");
	        Pageable pageable = PageRequest.of(0, 1, sortByRevenueDesc);
	        List<Site> sites = siteRepository.findAll(pageable).getContent();
	        if (!sites.isEmpty()) {
	            return sites.get(0);
	        }
	        return null;
	    }

		@Override
		public Site save(Site site) {
			// TODO Auto-generated method stub
			return siteRepository.saveAndFlush(site);
		}

		
		@Override
		public List<Site> getSites() {
			// TODO Auto-generated method stub
			return 	siteRepository.findAll();
		}

		@Override
		public void deleteSites(Long id) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void deleteSiteById(Long id) {
		    siteRepository.deleteById(id);
			
		}

		@Override
		public Site updateSiteById(Site o, Long id) {
			// TODO Auto-generated method stub
			Site cl= siteRepository.findById(id).orElse(null);

cl.setShopName(o.getShopName());
cl.setUrl(o.getUrl());
cl.setNameTechnician(o.getNameTechnician());
cl.setDescription(o.getDescription());
cl.setCategory(o.getCategory());
cl.setEmailSite(o.getEmailSite());
cl.setTechnicianName(o.getTechnicianName());

cl.setPhoneNumberSite(o.getPhoneNumberSite());
cl.setRevenue(o.getRevenue());
cl.setActivitySector(o.getActivitySector());
cl.setCompleteAddress(o.getCompleteAddress());

return siteRepository.save(cl);
		}

		@Override
		public Site findById(Long id) {
			// TODO Auto-generated method stub

			
			
			return siteRepository.findById(id).orElse(null);

		}
	}
	
	

