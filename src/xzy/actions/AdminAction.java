package xzy.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import hibernate.HibernateSessionFactory;
import xzy.beans.Admin;
import xzy.beans.User;
import xzy.dao.AdminDAO;

public class AdminAction extends ActionSupport implements SessionAware{
	private String adminName,adminEmail,adminPassword,adminId;
	private Map<String, Object> session;
	
	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	

	public String execute() throws Exception{
		return super.execute();
	}
	
	public String loginAdmin() throws Exception{
		AdminDAO adminDAO = new AdminDAO();
		List<Admin> list = adminDAO.findAll();
		for (Admin admin : list) {
			if (admin.getUserEmail().equals(adminEmail)&&
					admin.getUserPassword().equals(adminPassword)) {
				Admin currentAdmin = new Admin();
				currentAdmin.setUserId(admin.getUserId());
				currentAdmin.setUserName(admin.getUserName());
				currentAdmin.setUserEmail(admin.getUserEmail());
				currentAdmin.setUserPassword(admin.getUserPassword());
				//在session中添加对象
				session.put("currentAdmin", currentAdmin);
				return "loginSuccess";
			}
		}
		addFieldError("wrongLogin", "您的登陆名或密码错误!");
		return ERROR;
	}
	public String updateAdmin() throws Exception{
		try {
			//不重名后进行后续操作
			Session sess = HibernateSessionFactory.getSession();
			Transaction ts = sess.beginTransaction();
			try {
				Admin admin = sess.get(Admin.class, Long.parseLong(adminId));
				admin.setUserName(adminName);
				admin.setUserEmail(adminEmail);
				admin.setUserPassword(adminPassword);
				sess.update(admin);
				ts.commit();
				sess.close();
				session.remove("currentAdmin");
				session.put("currentAdmin", admin);
				return "updateSuccess";
			} catch (Exception e) {
				if(ts!=null){  
	                ts.rollback();       //事务回滚               
	            }  
	            e.printStackTrace();  
				addFieldError("updateError", "个人信息修改更新失败");
				return  INPUT;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}

	
}
