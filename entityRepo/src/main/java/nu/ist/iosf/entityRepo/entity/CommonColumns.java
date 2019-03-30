package nu.ist.iosf.entityRepo.entity;

import nu.ist.iosf.entityRepo.utils.DBProps;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class CommonColumns {

	@Column(name = "STATUS")
	protected Integer status;

	@Column(name = "DESCRIPTION", length = DBProps.description)
	private String description;

	@Column(name = "CREATED_BY", length = DBProps.textField)
	protected String createdBy;

	@Column(name = "UPDATED_BY", length = DBProps.textField)
	protected String updatedBy;

	@Column(name = "CREATED_DATE")
	@CreationTimestamp
	protected LocalDateTime createdDate;

	@Column(name = "UPDATED_DATE")
	@UpdateTimestamp
	protected LocalDateTime updatedDate;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
