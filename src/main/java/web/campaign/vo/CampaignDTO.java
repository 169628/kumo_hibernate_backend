package web.campaign.vo;

import java.sql.Timestamp;

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

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSv() {
		return sv;
	}

	public void setSv(String sv) {
		this.sv = sv;
	}

	public String getTv() {
		return tv;
	}

	public void setTv(String tv) {
		this.tv = tv;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Boolean getIsTestMode() {
		return isTestMode;
	}

	public void setIsTestMode(Boolean isTestMode) {
		this.isTestMode = isTestMode;
	}

	public String getTestList() {
		return testList;
	}

	public void setTestList(String testList) {
		this.testList = testList;
	}

	public String getDownloadBy() {
		return downloadBy;
	}

	public void setDownloadBy(String downloadBy) {
		this.downloadBy = downloadBy;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
