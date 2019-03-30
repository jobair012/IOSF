package nu.ist.iosf.entityRepo.entity;

import nu.ist.iosf.entityRepo.utils.DBProps;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_GROUP")
public class UserGroup extends CommonColumns {

    @Id
    @Column(name = "ID", length = DBProps.textField)
    private String id;

    @Column(name = "NAME", nullable = false, length = DBProps.textField)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
