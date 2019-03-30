package nu.ist.iosf.entityRepo.repository;

import nu.ist.iosf.entityRepo.entity.UserToken;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserTokenRepo extends BaseRepository<UserToken> {

    public UserToken getByUserToken(String token){

        String hql = "FROM UserToken userToken WHERE userToken.token = :token";
        Query<UserToken> query = getSession().createQuery(hql);
        query.setParameter("token", token);

        return query.uniqueResult();
    }
}
