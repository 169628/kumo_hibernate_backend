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
import web.campaign.dto.CampaignDTO;
import web.campaign.service.CampaignService;

@WebServlet("/campaign/delete")
public class DeleteCampaignController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CampaignService campaignService;

	@Override
	public void init() throws ServletException {
		campaignService = CommonUtil.getBean(getServletContext(), CampaignService.class);
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
		CampaignDTO campaign = gson.fromJson(req.getReader(), CampaignDTO.class);
		String errMsg = campaignService.delete(campaign);
		JsonObject respBody = new JsonObject();
		boolean success = errMsg == null;
		respBody.addProperty("success", success);
		if (!success) {
			respBody.addProperty("errMsg", errMsg);
		}
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setContentType("application/json");
		resp.getWriter().write(respBody.toString());
	}

}
