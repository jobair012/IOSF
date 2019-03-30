package nu.ist.iosf.entityRepo.entity;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLE_GROUP")
public class UserRoleGroup extends CommonColumns{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ROLE_GROUP_ID")
	private RoleGroup roleGroup;

	@ManyToOne
	@JoinColumn(name = "SYSTEM_USER_ID")
	private SystemUser systemUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleGroup getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(RoleGroup roleGroup) {
		this.roleGroup = roleGroup;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

}

