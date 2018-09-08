package xzy.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import hibernate.HibernateSessionFactory;
import xzy.beans.User;
import xzy.dao.UserDAO;

public class LoginAction extends ActionSupport implements SessionAware {
	
	private String useremail,userpassword,username;
	private Map<String, Object> session; 

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}
	
	@SuppressWarnings("unchecked")
	public String register() throws Exception {
		/*// 实例化Configuration，
		Configuration conf = new Configuration()
				// 不带参数的configure()方法默认加载hibernate.cfg.xml文件，
				// 如果传入abc.xml作为参数，则不再加载hibernate.cfg.xml，改为加载abc.xml
				.configure();
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
				.build();
		// 以Configuration实例创建SessionFactory实例
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();*/
		
		//建立session对象
		Session sess = HibernateSessionFactory.getSession();
		
		// 开始事务
		Transaction tx = sess.beginTransaction();
		User user = new User();
		UserDAO userDAO = new UserDAO();
		List<User> useList = userDAO.findAll();
		for (User a : useList) {
			if (useremail.equals(a.getUserEmail())) {
				addFieldError("sameEmail", "您的邮箱已经有人注册了！");
				return INPUT;
			}
		}
		user.setUserEmail(useremail);
		user.setUserName(username);
		user.setUserPassword(userpassword);
		user.setPostNumber(0l);
		sess.save(user);
		tx.commit();
		// 关闭Session
		sess.close();
		
		return "registersuccess";
	}
	
	@Override
	public String execute() throws Exception {
		
		Session sess = HibernateSessionFactory.getSession();
		//对user数据表进行查询
		Query query = sess.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		//定义查询后的User
		User currentuser = new User();
		for (User user : list) {
			if (useremail.equals(user.getUserEmail())
					&&userpassword.equals(user.getUserPassword())) {
				//初始化用户对象
				currentuser.setUserId(user.getUserId());
				currentuser.setUserName(user.getUserName());
				currentuser.setUserEmail(user.getUserEmail());
				currentuser.setUserPassword(user.getUserPassword());
				currentuser.setPostNumber(user.getPostNumber());
				currentuser.setUserHead(user.getUserHead());
				//在session中添加对象
				session.put("currentUser", currentuser);
				return SUCCESS;
			}
		}
		addFieldError("wrongUser", "用户名或密码不正确，请重新输入");
		return "loginError";
	}

	
	
	
}
