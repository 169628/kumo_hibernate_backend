package core.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import web.campaign.dto.CampaignDTO;

@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Core implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	private CampaignDTO campaignDTO;
	private List<CampaignDTO> campaignList;
}
