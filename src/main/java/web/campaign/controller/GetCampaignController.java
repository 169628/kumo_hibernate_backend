package web.campaign.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.campaign.service.CampaignService;
import web.campaign.service.impl.CampaignServiceImpl;






@WebServlet("/campaign/getAll")
public class GetCampaignController extends HttpServlet {
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var campaigns = campaignService.getCampaign();
		
		JsonObject respBody = new JsonObject();
		Gson gson = new Gson();
		boolean success = campaigns != null;
		respBody.addProperty("success", success);
		respBody.add("campaign", gson.toJsonTree(campaigns));
		
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json");
		resp.getWriter().write(respBody.toString());

	}
	
	

}
