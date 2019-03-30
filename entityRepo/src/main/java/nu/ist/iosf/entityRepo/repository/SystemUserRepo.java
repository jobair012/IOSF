package nu.ist.iosf.entityRepo.repository;

import nu.ist.iosf.commons.models.appBasic.IOSFSearchResult;
import nu.ist.iosf.commons.models.request.SystemUserSearchRequest;
import nu.ist.iosf.commons.utils.IOSFUtils;
import nu.ist.iosf.entityRepo.entity.SystemUser;
import nu.ist.iosf.entityRepo.entity.UserRoleGroup;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SystemUserRepo extends BaseRepository<SystemUser> {

    public SystemUser getUserByUsername(String username) {
        String hql = "FROM SystemUser systemUser WHERE systemUser.username = :username";
        Query<SystemUser> query = getSession().createQuery(hql);
        query.setParameter("username", username);

        return query.uniqueResult();
    }

    public Integer getUserStatusByUsername(String username) {
        String hql = "SELECT SU.status as status FROM SystemUser SU WHERE SU.username = :username";
        Query query = getSession().createQuery(hql);
        query.setParameter("username", username);
        return (Integer) query.uniqueResult();
    }

    public IOSFSearchResult searchSystemUser(SystemUserSearchRequest request) {

        IOSFSearchResult searchResult = new IOSFSearchResult();

        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(SystemUser.class);
        Root root = criteriaQuery.from(SystemUser.class);

        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(request.getUserId())) {
            predicates.add(criteriaBuilder.equal(root.get("id"), request.getUserId()));
        }
        if (!StringUtils.isEmpty(request.getStatus())) {
            predicates.add(criteriaBuilder.equal(root.get("status"), request.getStatus()));
        }
        if (!StringUtils.isEmpty(request.getFromDate())) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), IOSFUtils.getStartOfDate(request.getFromDate())));
        }
        if (!StringUtils.isEmpty(request.getToDate())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), IOSFUtils.getEndOfDate(request.getToDate())));
        }
        if (!StringUtils.isEmpty(request.getFullName())) {
            predicates.add(criteriaBuilder.like(root.join("systemUserDetails").get("fullName"), "%" + request.getFullName() + "%"));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));

        Query query = getSession().createQuery(criteriaQuery);
        Long totalRow = Long.valueOf(this.filterSystemUserByRole(query.list(), request.getRole()).size());


        if(request.getLimit() != -1){
            query.setFirstResult(request.getOffset()).setMaxResults(request.getLimit());
        }
        List<SystemUser> systemUserList = this.filterSystemUserByRole(query.list(), request.getRole());

        searchResult.setRows(systemUserList);
        searchResult.setTotal(totalRow);
        return searchResult;
    }

    private List<SystemUser> filterSystemUserByRole(List<SystemUser> systemUserList, String role){
        if (!StringUtils.isEmpty(role)) {
            systemUserList.removeIf(systemUser -> {
                Boolean removabel = false;
                for (UserRoleGroup userRoleGroup : systemUser.getUserRoleGroup()) {
                    if (!role.equals(userRoleGroup.getRoleGroup().getUserGroup().getId())) {
                        removabel = true;
                        break;
                    }
                }
                return removabel;
            });
        }
        return systemUserList;
    }

//    public void updateSystemUser(UserCrupRequest request) {
//        String hql = "UPDATE SystemUser SET password = :password, status = :status WHERE id = :id";
//        Query query = getSession().createQuery(hql);
//        query.setParameter("password", request.getPassword());
//        query.setParameter("status", request.getStatus());
//        query.setParameter("id", request.getUserId());
//        query.executeUpdate();
//    }

    /*public SystemUserDetailsResponse getUserDetailsById(Long id) {

        String hql = "SELECT new map(" +
                "   SU.id as id, SU.username as username, SU.status as status, SU.createdBy as createdBy, SU.updatedBy as updatedBy, SU.createdDate as createdDate," +
                "   SU.updatedDate as updatedDate, SUD.fullName as fullName )" +
                "   FROM SystemUser SU INNER JOIN SystemUserDetails SUD ON SU.id = SUD.systemUser.id WHERE SU.id = :id";

        Query query = getSession().createQuery(hql);
        query.setParameter("id", id);

        Map<String, Object> results = (Map<String, Object>) query.uniqueResult();
        SystemUserDetailsResponse systemUserDetailsResp = new ObjectMapper().convertValue(results, SystemUserDetailsResponse.class);


        return systemUserDetailsResp;
    }*/
}
