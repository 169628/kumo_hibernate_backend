package web.campaign.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "download_by_ref")
public class DownloadByRef {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "download_by_id")
	private Integer downloadById;
	private String content;
	
}
