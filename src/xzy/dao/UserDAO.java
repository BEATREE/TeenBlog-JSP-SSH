package xzy.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xzy.beans.Posts;
import xzy.beans.User;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see xzy.dao.User
 * @author MyEclipse Persistence Tools
 */
public class UserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_NAME = "userName";
	public static final String USER_PASSWORD = "userPassword";
	public static final String POST_NUMBER = "postNumber";
	public static final String USER_HEAD="userHead";
	
	
	public UserDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().save(transientInstance);
			ts.commit();
			getSession().close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().delete(persistentInstance);
			ts.commit();
			getSession().close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Long id) {
		log.debug("getting User instance with id: " + id);
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			User instance = (User) getSession().get("xzy.beans.User", id);
			ts.commit();
			getSession().close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			List results = getSession().createCriteria("xzy.beans.User").add(Example.create(instance)).list();
			ts.commit();
			getSession().close();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName + ", value: " + value);
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			String queryString = "from User as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			@SuppressWarnings("unchecked")
			List<Posts> list = queryObject.list();
			ts.commit();
			getSession().close();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserEmail(Object userEmail) {
		return findByProperty(USER_EMAIL, userEmail);
	}
	public List findByUserHead(Object userHead) {
		return findByProperty(USER_HEAD, userHead);
	}
	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByUserPassword(Object userPassword) {
		return findByProperty(USER_PASSWORD, userPassword);
	}

	public List findByPostNumber(Object postNumber) {
		return findByProperty(POST_NUMBER, postNumber);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			String queryString = "from User";
			Query queryObject = getSession().createQuery(queryString);
			@SuppressWarnings("unchecked")
			List<Posts> list = queryObject.list();
			ts.commit();
			getSession().close();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			User result = (User) getSession().merge(detachedInstance);
			ts.commit();
			getSession().close();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().saveOrUpdate(instance);
			ts.commit();
			getSession().close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			ts.commit();
			getSession().close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}