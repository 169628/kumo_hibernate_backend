package web.campaign.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import core.util.CommonUtil;
import web.campaign.service.CampaignService;






@WebServlet("/campaign/getAll")
public class GetCampaignController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CampaignService campaignService;
	
	@Override
	public void init() throws ServletException {
		campaignService = CommonUtil.getBean(getServletContext(), CampaignService.class);
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
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().write(respBody.toString());

	}
	
	

}
