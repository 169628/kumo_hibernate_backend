package web.campaign.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
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
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm",timezone = "GMT+8")
	private Timestamp createAt;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm",timezone = "GMT+8")
	private Timestamp updateAt;
	private Boolean isDeleted;

}
