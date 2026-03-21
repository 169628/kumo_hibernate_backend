package web.campaign.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import core.pojo.Core;
import web.campaign.dto.CampaignDTO;
import web.campaign.service.CampaignService;





@Controller
@RequestMapping("campaign")
public class GetCampaignController {
	
	@Autowired
	private CampaignService campaignService;
	
	@GetMapping("getAll")
	@ResponseBody
	public Core getAll() {
		final Core core = new Core();
		List<CampaignDTO> campaigns = new ArrayList<>();
		campaigns = campaignService.getCampaign();
		
		boolean success = campaigns != null;
		core.setSuccess(success);
		core.setCampaignList(campaigns);
		
		return core;
	}
	
	

}
