package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Site;

public interface SiteService {
	public Site getSiteWithHighestRevenue();
	public  Site save( Site  site);
	void deleteSites(Long id);
	public List<Site> getSites();
	public void deleteSiteById(Long id);
	Site updateSiteById(Site o,Long id);
	Site findById(Long id);

}
