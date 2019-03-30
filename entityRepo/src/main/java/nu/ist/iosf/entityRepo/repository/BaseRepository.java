package nu.ist.iosf.entityRepo.repository;

import nu.ist.iosf.commons.models.appBasic.KeyValue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseRepository<T extends Object> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> modelClass;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected IOSFCriteriaRepo<T> getIOSFCriteriaRepo() {
		Session session = getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getModelClass());
		Root<T> root = criteriaQuery.from(getModelClass());
		return new IOSFCriteriaRepo<T>(session, criteriaBuilder, criteriaQuery, root);
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getModelClass() {
		if (modelClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			this.modelClass = (Class<T>) thisType.getActualTypeArguments()[0];
		}
		return modelClass;
	}

	public List<KeyValue> getKeyValue(String keyFieldName, String valueFieldName, Map<String, Object> mapData){
		StringBuilder hql = new StringBuilder("SELECT "+keyFieldName+", "+valueFieldName+" FROM "+getDomainClassName()+" WHERE 1=1");
		if(!ObjectUtils.isEmpty(mapData)){
			mapData.keySet().forEach(key->{
				hql.append(" AND "+key+" = "+mapData.get(key));
			});
		}

		Query query = getSession().createQuery(hql.toString());
		List<Object[]> listResult = query.list();
		List<KeyValue> keyValueList = new ArrayList<>();
		listResult.forEach(keyValue -> {
			keyValueList.add(new KeyValue(keyValue[0].toString(), keyValue[1].toString()));
		});
		return keyValueList;
	}

	private String getDomainClassName() {
		return getModelClass().getName();
	}

	public void create(T t) {
		getSession().save(t);
	}

	public T get(Serializable id) {
		return (T) getSession().get(getModelClass(), id);
	}

	public T load(Serializable id) {
		return (T) getSession().load(getModelClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getSession().createQuery("from " + getDomainClassName()).list();
	}

	public void update(T t) {
		getSession().update(t);
	}

	public void delete(T t) {
		getSession().delete(t);
	}

	public void deleteById(Serializable id) {
		delete(load(id));
	}

	public void deleteAll() {
		getSession().createQuery("delete from " + getDomainClassName()).executeUpdate();
	}

	public long count() {
		return (Long) getSession().createQuery("select count(*) from " + getDomainClassName()).uniqueResult();
	}

	public boolean exists(Serializable id) {
		return (get(id) != null);
	}
}
