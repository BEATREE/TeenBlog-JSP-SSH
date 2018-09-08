package xzy.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import hibernate.HibernateSessionFactory;
import xzy.beans.Image;
import xzy.beans.Posts;
import xzy.beans.User;
import xzy.dao.ImageDAO;
import xzy.dao.PostsDAO;
import xzy.dao.UserDAO;
import xzy.utils.FileName;
import xzy.utils.SimplifyText;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements SessionAware{
	@SuppressWarnings("unused")
	private SimplifyText simplifyText = new SimplifyText();
	private String userId,userName,userEmail,userPassword;
	private Map<String, Object> session;
	private File userHead;
	private String userHeadFileName,userHeadContentType;
	private String savePath;
	private String allowType;	//���������
	
	
	
	public File getUserHead() {
		return userHead;
	}

	public void setUserHead(File userHead) {
		this.userHead = userHead;
	}

	public String getUserHeadFileName() {
		return userHeadFileName;
	}

	public void setUserHeadFileName(String userHeadFileName) {
		this.userHeadFileName = userHeadFileName;
	}

	public String getUserHeadContentType() {
		return userHeadContentType;
	}

	public void setUserHeadContentType(String userHeadContentType) {
		this.userHeadContentType = userHeadContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getAllowType() {
		return allowType;
	}

	public void setAllowType(String allowType) {
		this.allowType = allowType;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	//���ϴ�ͼƬ�����ͽ��м���
		public boolean check(String type){  	
	        String[] types=allowType.split(",");  
	        for(String s:types){  
	            if(s.equals(type)){  
	                return true;  
	            }  
	        }  
	        return false;  
	    }  
		//��֤��С
		public boolean checkSize(File file){  	
	        if (file!=null) {
			
		       if (file.length()<20971520) {//С��10M
				return true;
			}
		   	
			}
		       return false;
		}  
		//�Զ���������
	    public void  validateUpdateProfile(){  	
	    	if (userHeadFileName!=null) {
	    		//��������ͼƬ�ĸ�ʽ
		        boolean b=check(userHeadContentType);  
		        boolean a = checkSize(userHead);
		        if(!b){  
		            addFieldError("featuredPictureUpload","ͷ��ͼƬ��ʽ����");  
		        }  
		        if(!a){  
		            addFieldError("featuredPictureUpload","�ļ�ͼƬ��С������Χ");  
		        }  
			}
	    }  
	  //�Զ���������
	    public void  validateAupdateProfile(){  	
	    	if (userHeadFileName!=null) {
	    		//��������ͼƬ�ĸ�ʽ
		        boolean b=check(userHeadContentType);  
		        boolean a = checkSize(userHead);
		        if(!b){  
		            addFieldError("featuredPictureUpload","ͷ��ͼƬ��ʽ����");  
		        }  
		        if(!a){  
		            addFieldError("featuredPictureUpload","�ļ�ͼƬ��С������Χ");  
		        }    
			}
	    }
	//�Ը�����Ϣ���и���
	public String updateProfile() throws Exception{
		//�ж�email�Ƿ�����
		UserDAO userDAO = new UserDAO();
		List<User> useList = userDAO.findAll();
		for (User a : useList) {
			if (userEmail.equals(a.getUserEmail())) {
				if (Long.parseLong(userId)!=a.getUserId()) {
					addFieldError("sameEmail", "���������Ѿ�����ע���ˣ�");
					return INPUT;
				}
			}
		}
		if(getUserHeadFileName()!=null){
			//���ͼƬ���ϴ�
			String fname = FileName.reName(getUserHeadFileName());
	    	try {
	    		FileInputStream fis = new FileInputStream(getUserHead().getAbsolutePath());  
	            FileOutputStream  fos=new FileOutputStream(ServletActionContext.getServletContext().
	            		getRealPath(getSavePath())+ "\\" + fname); 
	            File file = new File(getSavePath());
	            
	            //�ļ����ƿ�ʼ
	            if (!file.exists()) {
	    			file.mkdirs();
	    		}
	            
	            int len=0;  
	            while((len=fis.read())!=-1){  
	            	fos.write(len);
	            }  
	            fos.close();  
	            fis.close();  
	          //�������
			} catch (Exception e) {
				e.printStackTrace();
				addFieldError("copyError", "ͷ���ϴ�����");
				return ERROR;
			}
		//����������к�������
		Session sess = HibernateSessionFactory.getSession();
		Transaction ts = sess.beginTransaction();
		try {
			User user = sess.get(User.class, Long.parseLong(userId));
			user.setUserName(userName);
			user.setUserEmail(userEmail);
			user.setUserPassword(userPassword);
			user.setUserHead(getSavePath()+ "\\" + fname);
			System.out.println(userName);
			sess.update(user);
			ts.commit();
			
			session.remove("currentUser");
			sess.refresh(user);
			sess.close();
			session.put("currentUser", user);
			return "updateSuccess";
		} catch (Exception e) {
			if(ts!=null){  
                ts.rollback();       //����ع�               
            }  
            e.printStackTrace();  
			addFieldError("updateError", "������Ϣ�޸ĸ���ʧ��");
			return  INPUT;
		}
		}else{
			//����������к�������
			Session sess = HibernateSessionFactory.getSession();
			Transaction ts = sess.beginTransaction();
			try {
				User user = sess.get(User.class, Long.parseLong(userId));
				user.setUserName(userName);
				user.setUserEmail(userEmail);
				user.setUserPassword(userPassword);
				System.out.println(userName);
				sess.update(user);
				ts.commit();
				session.remove("currentUser");
				sess.refresh(user);
				sess.close();
				session.put("currentUser", user);
				return "updateSuccess";
			} catch (Exception e) {
				if(ts!=null){  
	                ts.rollback();       //����ع�               
	            }  
	            e.printStackTrace();  
				addFieldError("updateError", "������Ϣ�޸ĸ���ʧ��");
				return  INPUT;
			}
		}
	}
	
	//����Ա�޸��û���Ϣ
	//�Ը�����Ϣ���и���
		public String aupdateProfile() throws Exception{
			//�ж�email�Ƿ�����
			UserDAO userDAO = new UserDAO();
			List<User> useList = userDAO.findAll();
			for (User a : useList) {
				if (userEmail.equals(a.getUserEmail())) {
					if (Long.parseLong(userId)!=a.getUserId()) {
						addFieldError("sameEmail", "�������Ѿ�����ע���ˣ�");
						return INPUT;
					}
				}
			}
			if(getUserHeadFileName()!=null){
				//���ͼƬ���ϴ�
				String fname = FileName.reName(getUserHeadFileName());
		    	try {
		    		FileInputStream fis = new FileInputStream(getUserHead().getAbsolutePath());  
		            FileOutputStream  fos=new FileOutputStream(ServletActionContext.getServletContext().
		            		getRealPath(getSavePath())+ "\\" + fname); 
		            File file = new File(getSavePath());
		            
		            //�ļ����ƿ�ʼ
		            if (!file.exists()) {
		    			file.mkdirs();
		    		}
		            
		            int len=0;  
		            while((len=fis.read())!=-1){  
		            	fos.write(len);
		            }  
		            fos.close();  
		            fis.close();  
		          //�������
				} catch (Exception e) {
					e.printStackTrace();
					addFieldError("copyError", "ͷ���ϴ�����");
					return ERROR;
				}
			//����������к�������
			Session sess = HibernateSessionFactory.getSession();
			Transaction ts = sess.beginTransaction();
			try {
				User user = sess.get(User.class, Long.parseLong(userId));
				user.setUserName(userName);
				user.setUserEmail(userEmail);
				user.setUserPassword(userPassword);
				user.setUserHead(getSavePath()+ "\\" + fname);
				System.out.println(userName);
				sess.update(user);
				ts.commit();
				sess.close();
				return "aupdateSuccess";
			} catch (Exception e) {
				if(ts!=null){  
	                ts.rollback();       //����ع�               
	            }  
	            e.printStackTrace();  
				addFieldError("aupdateError", "������Ϣ�޸ĸ���ʧ��");
				return  INPUT;
			}
			}else{
				//����������к�������
				Session sess = HibernateSessionFactory.getSession();
				Transaction ts = sess.beginTransaction();
				try {
					User user = sess.get(User.class, Long.parseLong(userId));
					user.setUserName(userName);
					user.setUserEmail(userEmail);
					user.setUserPassword(userPassword);
					System.out.println(userName);
					sess.update(user);
					ts.commit();
					sess.close();
					return "aupdateSuccess";
				} catch (Exception e) {
					if(ts!=null){  
		                ts.rollback();       //����ع�               
		            }  
		            e.printStackTrace();  
					addFieldError("aupdateError", "������Ϣ�޸ĸ���ʧ��");
					return  INPUT;
				}
			}
		}
		@SuppressWarnings("unchecked")
		public String asearchProfile() throws Exception{
	    	UserDAO userDAO = new UserDAO();
	    	try {
	    		List<User> allUserList = userDAO.findAll();
	    		List<User> userlist = new ArrayList<User>();
	    		for (User user : allUserList) {
					if (user.getUserName().toLowerCase().contains(userName.toLowerCase())) {
						userlist.add(user);
					}
				}
	    		session.put("userResults", userlist);
	        	return "asearchSuccess";
			} catch (Exception e) {
				addFieldError("searchError", "�û���Ϣ����ʧ��");
				return "input";
			}
	    	
	    }
		@SuppressWarnings("unchecked")
		public String deleteProfile() throws Exception{
			UserDAO uDao = new UserDAO();//����IDAo��������ִ��ɾ������
			/*ImageDAO imgDao = new ImageDAO();	//����imageDao����ɾ�����ͼƬ
			PostsDAO postsDao = new PostsDAO();//����PostsDao����ɾ���������
*/			String uid =  ServletActionContext. getRequest().getParameter("uId");
			Long long1 = Long.parseLong(uid);
			try {
				User user = uDao.findById(long1);
				/*//��ȡ�û���Ӧ�����º�ͼƬ
				List<Image> imgList = imgDao.findByUser(user);
				List<Posts> postList = postsDao.findByUser(user);*/
				uDao.delete(user);//ɾ���û�
				//���㼶�������𣿣������������
				/*for (Image image : imgList) {
					imgDao.delete(image);
				}
				for (Posts posts : postList) {
					postsDao.delete(posts);
				}*/
				return "deleteSuccess";
			} catch (Exception e) {
				addFieldError("deleteError", "ɾ����Ϣʧ�ܣ�");
				return INPUT;
			}
		}
	
}
