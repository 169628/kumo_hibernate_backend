package web.campaign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import core.pojo.Core;
import web.campaign.dto.CampaignDTO;
import web.campaign.service.CampaignService;

@Controller
@RequestMapping("campaign")
public class GetOneCampaignController {
	
	@Autowired
	private CampaignService campaignService;
	
	@GetMapping("getOne")
	@ResponseBody
	public Core getOne(@RequestParam String no){
		final Core core = new Core();
		Integer campaignNo = Integer.parseInt(no);
		CampaignDTO campaignDTO = campaignService.getOneCampaign(campaignNo);
		
		boolean success = campaignDTO != null;
		core.setSuccess(success);
		core.setCampaignDTO(campaignDTO);
		
		return core;
	}

}
