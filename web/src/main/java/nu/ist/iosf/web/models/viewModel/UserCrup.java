package nu.ist.iosf.web.models.viewModel;

import nu.ist.iosf.commons.models.appBasic.KeyValue;
import nu.ist.iosf.commons.models.request.validationGroup.Update;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class UserCrup {

	@NotNull(groups = Update.class)
	private Long userId;

	@NotBlank(message = "{message.validation.required}")
	private String username;

//	@NotBlank(message = "{message.validation.required}")
	private String password;

//	@NotBlank(message = "{message.validation.required}")
	private String confirmPassword;

	@Email(message = "{message.validation.valid.email}")
	private String emailAddress;

	@Pattern(regexp = "01[0-9]{9}", message = "{message.validation.valid.contactNo}")
	private String contactNo;

	@NotBlank(message = "{message.validation.required}")
	private String fullName;

	@NotBlank(message = "{message.validation.required}")
	private String role;

	@NotNull(message = "{message.validation.required}")
	private Integer status;

	private String remarks;
	private String actionState;

	private String createdBy;
	private String updatedBy;
	private String createdOn;
	private String updatedOn;

	private List<KeyValue> roleList;
	private List<KeyValue> statusList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getActionState() {
		return actionState;
	}

	public void setActionState(String actionState) {
		this.actionState = actionState;
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

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<KeyValue> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<KeyValue> roleList) {
		this.roleList = roleList;
	}

	public List<KeyValue> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<KeyValue> statusList) {
		this.statusList = statusList;
	}
}
