package nu.ist.iosf.entityRepo.repository;

import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class IOSFCriteriaRepo<T> {

	private Session session;
	private CriteriaBuilder criteriaBuilder;
	private CriteriaQuery<T> criteriaQuery;
	private Root<T> root;
	
	public IOSFCriteriaRepo() {
	}

	public IOSFCriteriaRepo(Session session, CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery, Root<T> root) {
		this.session = session;
		this.criteriaBuilder = criteriaBuilder;
		this.criteriaQuery = criteriaQuery;
		this.root = root;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}

	public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
		this.criteriaBuilder = criteriaBuilder;
	}

	public CriteriaQuery<T> getCriteriaQuery() {
		return criteriaQuery;
	}

	public void setCriteriaQuery(CriteriaQuery<T> criteriaQuery) {
		this.criteriaQuery = criteriaQuery;
	}

	public Root<T> getRoot() {
		return root;
	}

	public void setRoot(Root<T> root) {
		this.root = root;
	}

}
