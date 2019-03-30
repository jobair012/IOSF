package nu.ist.iosf.entityRepo.entity;

import javax.persistence.*;

@Entity
@Table(name = "ROLE_GROUP")
public class RoleGroup extends CommonColumns{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "USER_ROLE_ID")
	private UserRole userRole;

	@ManyToOne
	@JoinColumn(name = "USER_GROUP_ID")
	private UserGroup userGroup;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

}
