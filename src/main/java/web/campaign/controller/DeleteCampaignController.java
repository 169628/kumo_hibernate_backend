package web.campaign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import core.pojo.Core;
import web.campaign.dto.CampaignDTO;
import web.campaign.service.CampaignService;

@Controller
@RequestMapping("campaign")
public class DeleteCampaignController {
	
	@Autowired
	private CampaignService campaignService;

	@PostMapping("delete")
	@ResponseBody
	public Core delete(@RequestBody CampaignDTO campaignDTO){
		final Core core = new Core();
		String errMsg = campaignService.delete(campaignDTO);
		boolean success = errMsg == null;
		core.setSuccess(success);
		if(!success) {
			core.setMessage(errMsg);
		}
		return core;
	}

}
