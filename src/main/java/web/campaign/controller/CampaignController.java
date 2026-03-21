package web.campaign.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.pojo.Core;
import web.campaign.dto.CampaignDTO;
import web.campaign.service.CampaignService;

@RestController
@RequestMapping("api/campaign")
public class CampaignController {
	
	@Autowired
	private CampaignService campaignService;
	
	@GetMapping
	public Core getAll() {
		final Core core = new Core();
		List<CampaignDTO> campaigns = new ArrayList<>();
		campaigns = campaignService.getCampaign();
		
		boolean success = campaigns != null;
		core.setSuccess(success);
		core.setCampaignList(campaigns);
		
		return core;
	}
	
	@GetMapping("{no}")
	public Core getOne(@PathVariable String no){
		final Core core = new Core();
		Integer campaignNo = Integer.parseInt(no);
		CampaignDTO campaignDTO = campaignService.getOneCampaign(campaignNo);
		
		boolean success = campaignDTO != null;
		core.setSuccess(success);
		core.setCampaignDTO(campaignDTO);
		
		return core;
	}
	
	@PostMapping
	public Core create(@RequestBody CampaignDTO campaignDTO){
		final Core core = new Core();
		String errMsg = campaignService.create(campaignDTO);
		boolean success = errMsg == null;
		core.setSuccess(success);
		if(!success) {
			core.setMessage(errMsg);
		}
		return core;
	}
	
	@PutMapping("{no}")
	public Core put(@PathVariable String no, @RequestBody CampaignDTO campaignDTO){
		final Core core = new Core();
		Integer campaignNo = Integer.parseInt(no);
		
		String errMsg = campaignService.put(campaignNo,campaignDTO);
		boolean success = errMsg == null;
		core.setSuccess(success);
		if(!success) {
			core.setMessage(errMsg);
		}
		return core;
		
	}
	
	@DeleteMapping("{no}")
	public Core delete(@PathVariable String no){
		final Core core = new Core();
		Integer campaignNo = Integer.parseInt(no);
		String errMsg = campaignService.delete(campaignNo);
		boolean success = errMsg == null;
		core.setSuccess(success);
		if(!success) {
			core.setMessage(errMsg);
		}
		return core;
	}
	

}
