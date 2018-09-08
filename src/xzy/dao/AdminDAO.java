package xzy.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xzy.beans.Admin;
import xzy.beans.Posts;

/**
 * A data access object (DAO) providing persistence and search support for Admin
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see xzy.dao.Admin
 * @author MyEclipse Persistence Tools
 */
public class AdminDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AdminDAO.class);
	// property constants
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_NAME = "userName";
	public static final String USER_PASSWORD = "userPassword";
	
	public AdminDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void save(Admin transientInstance) {
		log.debug("saving Admin instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().save(transientInstance);
			ts.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Admin persistentInstance) {
		log.debug("deleting Admin instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().delete(persistentInstance);
			ts.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Admin findById(java.lang.Long id) {
		log.debug("getting Admin instance with id: " + id);
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			Admin instance = (Admin) getSession().get("xzy.beans.Admin", id);
			ts.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Admin instance) {
		log.debug("finding Admin instance by example");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			List results = getSession().createCriteria("xzy.beans.Admin").add(Example.create(instance)).list();
			ts.commit();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Admin instance with property: " + propertyName + ", value: " + value);
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			String queryString = "from Admin as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			@SuppressWarnings("unchecked")
			List<Posts> list = queryObject.list();
			ts.commit();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserEmail(Object userEmail) {
		return findByProperty(USER_EMAIL, userEmail);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByUserPassword(Object userPassword) {
		return findByProperty(USER_PASSWORD, userPassword);
	}

	public List findAll() {
		log.debug("finding all Admin instances");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			String queryString = "from Admin";
			Query queryObject = getSession().createQuery(queryString);
			@SuppressWarnings("unchecked")
			List<Posts> list = queryObject.list();
			ts.commit();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Admin merge(Admin detachedInstance) {
		log.debug("merging Admin instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			Admin result = (Admin) getSession().merge(detachedInstance);
			ts.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Admin instance) {
		log.debug("attaching dirty Admin instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().saveOrUpdate(instance);
			ts.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Admin instance) {
		log.debug("attaching clean Admin instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			ts.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}