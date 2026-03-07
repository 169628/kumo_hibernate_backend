package web.campaign.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "campaigns")
public class Campaign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer no;
	@Column(name = "campaign_id", insertable = false)
	private Long campaignId;
	private String brand;
	private String model;
	private String sv;
	private String tv;
	private String file;
	@Column(name = "file_size")
	private Integer fileSize;
	@Column(name = "is_test_mode")
	private Boolean isTestMode;
	@Column(name = "test_list", nullable = true)
	private String testList;
	@Column(name = "download_by_id")
	private Integer downloadById;
	@Column(name = "is_enabled", insertable = false)
	private Boolean isEnabled;
	@Column(name = "create_at", insertable = false)
	private Timestamp createAt;
	@Column(name = "update_at", insertable = false)
	private Timestamp updateAt;
	@Column(name = "is_deleted", insertable = false)
	private Boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "download_by_id", insertable = false, updatable = false)
	private DownloadByRef downloadBy;

}