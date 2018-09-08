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
		/*// ʵ����Configuration��
		Configuration conf = new Configuration()
				// ����������configure()����Ĭ�ϼ���hibernate.cfg.xml�ļ���
				// �������abc.xml��Ϊ���������ټ���hibernate.cfg.xml����Ϊ����abc.xml
				.configure();
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
				.build();
		// ��Configurationʵ������SessionFactoryʵ��
		SessionFactory sf = conf.buildSessionFactory();
		Session sess = sf.openSession();*/
		
		//����session����
		Session sess = HibernateSessionFactory.getSession();
		
		// ��ʼ����
		Transaction tx = sess.beginTransaction();
		User user = new User();
		UserDAO userDAO = new UserDAO();
		List<User> useList = userDAO.findAll();
		for (User a : useList) {
			if (useremail.equals(a.getUserEmail())) {
				addFieldError("sameEmail", "���������Ѿ�����ע���ˣ�");
				return INPUT;
			}
		}
		user.setUserEmail(useremail);
		user.setUserName(username);
		user.setUserPassword(userpassword);
		user.setPostNumber(0l);
		sess.save(user);
		tx.commit();
		// �ر�Session
		sess.close();
		
		return "registersuccess";
	}
	
	@Override
	public String execute() throws Exception {
		
		Session sess = HibernateSessionFactory.getSession();
		//��user���ݱ���в�ѯ
		Query query = sess.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		//�����ѯ���User
		User currentuser = new User();
		for (User user : list) {
			if (useremail.equals(user.getUserEmail())
					&&userpassword.equals(user.getUserPassword())) {
				//��ʼ���û�����
				currentuser.setUserId(user.getUserId());
				currentuser.setUserName(user.getUserName());
				currentuser.setUserEmail(user.getUserEmail());
				currentuser.setUserPassword(user.getUserPassword());
				currentuser.setPostNumber(user.getPostNumber());
				currentuser.setUserHead(user.getUserHead());
				//��session����Ӷ���
				session.put("currentUser", currentuser);
				return SUCCESS;
			}
		}
		addFieldError("wrongUser", "�û��������벻��ȷ������������");
		return "loginError";
	}

	
	
	
}
