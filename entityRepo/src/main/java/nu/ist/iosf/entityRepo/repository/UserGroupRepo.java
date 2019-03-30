package nu.ist.iosf.entityRepo.repository;

import nu.ist.iosf.commons.enums.Status;
import nu.ist.iosf.entityRepo.entity.UserGroup;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserGroupRepo extends BaseRepository<UserGroup>{

    public List<UserGroup> getAllActiveUserGroup(){

        String hql = "FROM UserGroup userGroup WHERE userGroup.status = :status";
        Query<UserGroup> query = getSession().createQuery(hql, UserGroup.class);
        query.setParameter("status", Status.ACTIVE.getCode());

        return query.list();
    }
}


