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
import web.campaign.vo.CampaignDTO;

@WebServlet("/campaign/put")
public class PutCampaignController extends HttpServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer campaignNo = Integer.parseInt(req.getParameter("no"));
		Gson gson = new Gson();
		CampaignDTO campaign = gson.fromJson(req.getReader(), CampaignDTO.class);
		
		String errMsg = campaignService.put(campaignNo,campaign);
		JsonObject respBody = new JsonObject();
		boolean success = errMsg == null;
		respBody.addProperty("success", success);
		if(!success) {
			respBody.addProperty("errMsg", errMsg);
		}
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json");
		resp.getWriter().write(respBody.toString());
		
	}

}
