package nu.ist.iosf.entityRepo.repository;

import nu.ist.iosf.commons.enums.Status;
import nu.ist.iosf.entityRepo.entity.RoleGroup;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class RoleGroupRepo extends BaseRepository<RoleGroup>{

    public List<RoleGroup> getAllActiveByUserGroup(String userGroupId){

        String hql = "FROM RoleGroup roleGroup WHERE roleGroup.status = :status AND roleGroup.userGroup.id = :userGroupId";
        Query<RoleGroup> query = getSession().createQuery(hql, RoleGroup.class);
        query.setParameter("status", Status.ACTIVE.getCode());
        query.setParameter("userGroupId", userGroupId);

        return query.list();
    }
}
