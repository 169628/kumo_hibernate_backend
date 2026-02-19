package web.campaign.vo;

import java.sql.Timestamp;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CampaignDTO {
	private Integer no;
	private Long campaignId;
	private String brand;
	private String model;
	private String sv;
	private String tv;
	private String file;
	private Integer fileSize;
	private Boolean isTestMode;
	private String testList;
	private String downloadBy;
	private Boolean isEnabled;
	private Timestamp createAt;
	private Timestamp updateAt;
	private Boolean isDeleted;

}
