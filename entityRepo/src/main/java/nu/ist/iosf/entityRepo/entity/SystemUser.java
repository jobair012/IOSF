package nu.ist.iosf.entityRepo.entity;

import nu.ist.iosf.entityRepo.utils.DBProps;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SYSTEM_USER")
public class SystemUser extends CommonColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @OneToOne(mappedBy = "systemUser", fetch = FetchType.EAGER)
    private SystemUserDetails systemUserDetails;

    @OneToMany(mappedBy = "systemUser", fetch = FetchType.EAGER)
    private List<UserRoleGroup> userRoleGroup;

    @Column(name = "USERNAME", nullable = false, unique = true, length = DBProps.textField)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = DBProps.textField)
    private String password;

    @Column(name = "BAD_LOGIN_ATTEMPTS")
    private Integer badLoginAttempts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUserDetails getSystemUserDetails() {
        return systemUserDetails;
    }

    public void setSystemUserDetails(SystemUserDetails systemUserDetails) {
        this.systemUserDetails = systemUserDetails;
    }

    public List<UserRoleGroup> getUserRoleGroup() {
        return userRoleGroup;
    }

    public void setUserRoleGroup(List<UserRoleGroup> userRoleGroup) {
        this.userRoleGroup = userRoleGroup;
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

    public Integer getBadLoginAttempts() {
        return badLoginAttempts;
    }

    public void setBadLoginAttempts(Integer badLoginAttempts) {
        this.badLoginAttempts = badLoginAttempts;
    }
}
