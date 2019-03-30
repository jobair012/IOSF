package nu.ist.iosf.entityRepo.repository;

import nu.ist.iosf.commons.models.response.SystemUserDetailsResponse;
import nu.ist.iosf.entityRepo.entity.SystemUserDetails;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SystemUserDetailsRepo extends BaseRepository<SystemUserDetails> {

    public List<SystemUserDetailsResponse> getUserByUsername(String username){
        String hql = "FROM SystemUser systemUser WHERE systemUser.username = :username";
        Query query = getSession().createQuery(hql, SystemUserDetailsResponse.class);
        query.setParameter("username", username);

        return query.getResultList();
    }
}
