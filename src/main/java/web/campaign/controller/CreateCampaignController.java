package web.campaign.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.campaign.service.CampaignService;
import web.campaign.service.impl.CampaignServiceImpl;
import web.campaign.vo.Campaign;

@WebServlet("/campaign/create")
public class CreateCampaignController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CampaignService campaignService;
	
	@Override
	public void init() throws ServletException {
		try {
			campaignService = new CampaignServiceImpl();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		Campaign campaign = gson.fromJson(req.getReader(), Campaign.class);
		
		String errMsg = campaignService.create(campaign);
		
//		System.out.println(campaign.getBrand());
//		System.out.println(campaign.getModel());
//		System.out.println(campaign.getSv());
//		System.out.println(campaign.getTv());
//		System.out.println(campaign.getFile());
//		System.out.println(campaign.getFileSize());
//		System.out.println(campaign.getIsTestMode());
//		System.out.println(campaign.getTestList());
//		System.out.println(campaign.getDownloadBy());
		
		resp.setHeader("Access-Control-Allow-Origin", "*");

	}

}
