package xzy.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xzy.beans.Posts;
import xzy.beans.User;

/**
 * A data access object (DAO) providing persistence and search support for Posts
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see xzy.dao.Posts
 * @author MyEclipse Persistence Tools
 */
public class PostsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(PostsDAO.class);
	// property constants
	public static final String POST_TITLE = "postTitle";
	public static final String POST_CONTENT = "postContent";
	public static final String POST_LABEL = "postLabel";
	public static final String POST_IMAGE = "postImage";
	public static final String POST_LIKES = "postLikes";
	private static final String USER_ID = "user";

	
	public PostsDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void save(Posts transientInstance) {
		log.debug("saving Posts instance");
		try {
			
			Transaction ts = getSession().beginTransaction();//开始事务
			getSession().save(transientInstance);
			ts.commit();										//提交事务
			getSession().close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Posts persistentInstance) {
		log.debug("deleting Posts instance");
		try {
			Transaction ts = getSession().beginTransaction();//开始事务
			getSession().delete(persistentInstance);
			log.debug("delete successful");
			ts.commit();
			getSession().close();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Posts findById(java.lang.Long id) {
		log.debug("getting Posts instance with id: " + id);
		try {
			Transaction ts = getSession().beginTransaction();//开始事务
			Posts instance = (Posts) getSession().get("xzy.beans.Posts", id);
			ts.commit();
			getSession().close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Posts instance) {
		log.debug("finding Posts instance by example");
		try { 
			Transaction ts = getSession().beginTransaction();//开始事务
			List results = getSession().createCriteria("xzy.beans.Posts").add(Example.create(instance)).list();
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
		log.debug("finding Posts instance with property: " + propertyName + ", value: " + value);
		try {
			Transaction ts = getSession().beginTransaction();//开始事务
			String queryString = "from Posts as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			@SuppressWarnings("unchecked")
			List<Posts> postList = queryObject.list();
			ts.commit();
			getSession().close();
			return postList;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPostTitle(Object postTitle) {
		return findByProperty(POST_TITLE, postTitle);
	}

	public List findByPostContent(Object postContent) {
		return findByProperty(POST_CONTENT, postContent);
	}

	public List findByPostLabel(Object postLabel) {
		return findByProperty(POST_LABEL, postLabel);
	}

	public List findByPostImage(Object postImage) {
		return findByProperty(POST_IMAGE, postImage);
	}

	public List findByPostLikes(Object postLikes) {
		return findByProperty(POST_LIKES, postLikes);
	}
	//通过用户来进行查找相关的信息
	public List findByUser(User user) {
		try {
			Transaction ts = getSession().beginTransaction();//开始事务
			String queryString = "from Posts where user_id=" + user.getUserId();
			Query queryObject = getSession().createQuery(queryString);
			@SuppressWarnings("unchecked")
			List<Posts> postList = queryObject.list();
			ts.commit();
			getSession().close();
			return postList;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}
	//热门文章内容
	public List findHotPosts() {
		log.debug("finding all Posts instances");
		try {
			Transaction ts = getSession().beginTransaction();//开始事务
			String queryString = "from Posts order by post_likes desc";
			Query queryObject = getSession().createQuery(queryString);
			@SuppressWarnings("unchecked")
			List<Posts> postList = queryObject.list();
			ts.commit();
			getSession().close();
			return postList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll() {
		log.debug("finding all Posts instances");
		try {
			Transaction ts = getSession().beginTransaction();//开始事务
			String queryString = "from Posts order by post_id desc";
			Query queryObject = getSession().createQuery(queryString);
			@SuppressWarnings("unchecked")
			List<Posts> postList = queryObject.list();
			ts.commit();
			getSession().close();
			return postList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Posts merge(Posts detachedInstance) {
		log.debug("merging Posts instance");
		try {
			Transaction ts = getSession().beginTransaction();//开始事务
			Posts result = (Posts) getSession().merge(detachedInstance);
			ts.commit();
			getSession().close();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Posts instance) {
		log.debug("attaching dirty Posts instance");
		try {
			Transaction ts = getSession().beginTransaction();//开始事务
			getSession().saveOrUpdate(instance);
			ts.commit();
			getSession().close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Posts instance) {
		log.debug("attaching clean Posts instance");
		try {
			Transaction ts = getSession().beginTransaction();//开始事务
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