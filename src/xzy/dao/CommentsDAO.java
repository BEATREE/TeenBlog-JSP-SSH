package xzy.dao;

import java.util.List;


import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xzy.beans.Comments;
import xzy.beans.Posts;

/**
 * A data access object (DAO) providing persistence and search support for
 * Comments entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see xzy.beans.Comments
 * @author MyEclipse Persistence Tools
 */
public class CommentsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(CommentsDAO.class);
	// property constants
	public static final String COMMENT_CONTENT = "commentContent";
	public static final String USER_NAME = "userName";

	public void save(Comments transientInstance) {
		log.debug("saving Comments instance");
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			session.save(transientInstance);
			ts.commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Comments persistentInstance) {
		log.debug("deleting Comments instance");
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			session.delete(persistentInstance);
			ts.commit();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Comments findById(java.lang.Long id) {
		log.debug("getting Comments instance with id: " + id);
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			Comments instance = (Comments) session.get("xzy.beans.Comments", id);
			ts.commit();
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Comments instance) {
		log.debug("finding Comments instance by example");
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			List results = session.createCriteria("xzy.beans.Comments").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			ts.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Comments instance with property: " + propertyName + ", value: " + value);
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			String queryString = "from Comments as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List list = queryObject.list();
			ts.commit();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCommentContent(Object commentContent) {
		return findByProperty(COMMENT_CONTENT, commentContent);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}
	public List findByPost(Posts post) {
		log.debug("finding Comments instance with property: " + post + ", value: " + post.toString());
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			String queryString = "from Comments where post_id=" + post.getPostId()+" order by comment_id desc";
			Query queryObject = session.createQuery(queryString);
			List<Comments> list = queryObject.list();
			ts.commit();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Comments instances");
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			String queryString = "from Comments order by comment_id desc";
			Query queryObject = session.createQuery(queryString);
			List list = queryObject.list();
			ts.commit();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Comments merge(Comments detachedInstance) {
		log.debug("merging Comments instance");
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			Comments result = (Comments) session.merge(detachedInstance);
			log.debug("merge successful");
			ts.commit();
			session.close();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Comments instance) {
		log.debug("attaching dirty Comments instance");
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			session.saveOrUpdate(instance);
			ts.commit();
			session.close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Comments instance) {
		log.debug("attaching clean Comments instance");
		try {
			Session session = getSession();
			Transaction ts = session.beginTransaction();
			session.buildLockRequest(LockOptions.NONE).lock(instance);
			ts.commit();
			session.close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}