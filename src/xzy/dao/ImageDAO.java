package xzy.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xzy.beans.Image;
import xzy.beans.Posts;
import xzy.beans.User;

/**
 * A data access object (DAO) providing persistence and search support for Image
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see xzy.dao.Image
 * @author MyEclipse Persistence Tools
 */
public class ImageDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ImageDAO.class);
	// property constants
	public static final String IMAGE_NAME = "imageName";
	public static final String IMAGE_DESC = "imageDesc";
	public static final String IMAGE_SRC = "imageSrc";

	
	public ImageDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void save(Image transientInstance) {
		log.debug("saving Image instance");
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

	public void delete(Image persistentInstance) {
		log.debug("deleting Image instance");
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

	public Image findById(java.lang.Long id) {
		log.debug("getting Image instance with id: " + id);
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			Image instance = (Image) getSession().get("xzy.beans.Image", id);
			ts.commit();
			getSession().close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Image instance) {
		log.debug("finding Image instance by example");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			List results = getSession().createCriteria("xzy.beans.Image").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			ts.commit();
			getSession().close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Image instance with property: " + propertyName + ", value: " + value);
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			String queryString = "from Image as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List lastList = queryObject.list();
			ts.commit();
			getSession().close();
			return lastList;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByImageName(Object imageName) {
		return findByProperty(IMAGE_NAME, imageName);
	}

	public List findByImageDesc(Object imageDesc) {
		return findByProperty(IMAGE_DESC, imageDesc);
	}

	public List findByImageSrc(Object imageSrc) {
		return findByProperty(IMAGE_SRC, imageSrc);
	}
	//通过用户找到上传的图片
	public List findByUser(User user) {
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			String queryString = "from Image where user_id=" + user.getUserId();
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
	public List findAll() {
		log.debug("finding all Image instances");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			String queryString = "from Image order by image_id desc";
			Query queryObject = getSession().createQuery(queryString);
			List lastList = queryObject.list();
			ts.commit();
			getSession().close();
			return lastList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Image merge(Image detachedInstance) {
		log.debug("merging Image instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			Image result = (Image) getSession().merge(detachedInstance);
			log.debug("merge successful");
			ts.commit();
			getSession().close();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Image instance) {
		log.debug("attaching dirty Image instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
			ts.commit();
			getSession().close();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Image instance) {
		log.debug("attaching clean Image instance");
		try {
			Transaction ts = getSession().beginTransaction();	//开始事务
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
			ts.commit();
			getSession().close();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}