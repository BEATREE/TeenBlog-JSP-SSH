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
	private String allowType;	//允许的类型
	
	
	
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

	//对上传图片的类型进行检验
		public boolean check(String type){  	
	        String[] types=allowType.split(",");  
	        for(String s:types){  
	            if(s.equals(type)){  
	                return true;  
	            }  
	        }  
	        return false;  
	    }  
		//验证大小
		public boolean checkSize(File file){  	
	        if (file!=null) {
			
		       if (file.length()<20971520) {//小于10M
				return true;
			}
		   	
			}
		       return false;
		}  
		//自定义拦截器
	    public void  validateUpdateProfile(){  	
	    	if (userHeadFileName!=null) {
	    		//用于拦截图片的格式
		        boolean b=check(userHeadContentType);  
		        boolean a = checkSize(userHead);
		        if(!b){  
		            addFieldError("featuredPictureUpload","头像图片格式错误");  
		        }  
		        if(!a){  
		            addFieldError("featuredPictureUpload","文件图片大小超出范围");  
		        }  
			}
	    }  
	  //自定义拦截器
	    public void  validateAupdateProfile(){  	
	    	if (userHeadFileName!=null) {
	    		//用于拦截图片的格式
		        boolean b=check(userHeadContentType);  
		        boolean a = checkSize(userHead);
		        if(!b){  
		            addFieldError("featuredPictureUpload","头像图片格式错误");  
		        }  
		        if(!a){  
		            addFieldError("featuredPictureUpload","文件图片大小超出范围");  
		        }    
			}
	    }
	//对个人信息进行更新
	public String updateProfile() throws Exception{
		//判断email是否重名
		UserDAO userDAO = new UserDAO();
		List<User> useList = userDAO.findAll();
		for (User a : useList) {
			if (userEmail.equals(a.getUserEmail())) {
				if (Long.parseLong(userId)!=a.getUserId()) {
					addFieldError("sameEmail", "您的邮箱已经有人注册了！");
					return INPUT;
				}
			}
		}
		if(getUserHeadFileName()!=null){
			//完成图片的上传
			String fname = FileName.reName(getUserHeadFileName());
	    	try {
	    		FileInputStream fis = new FileInputStream(getUserHead().getAbsolutePath());  
	            FileOutputStream  fos=new FileOutputStream(ServletActionContext.getServletContext().
	            		getRealPath(getSavePath())+ "\\" + fname); 
	            File file = new File(getSavePath());
	            
	            //文件复制开始
	            if (!file.exists()) {
	    			file.mkdirs();
	    		}
	            
	            int len=0;  
	            while((len=fis.read())!=-1){  
	            	fos.write(len);
	            }  
	            fos.close();  
	            fis.close();  
	          //复制完成
			} catch (Exception e) {
				e.printStackTrace();
				addFieldError("copyError", "头像上传出错");
				return ERROR;
			}
		//不重名后进行后续操作
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
                ts.rollback();       //事务回滚               
            }  
            e.printStackTrace();  
			addFieldError("updateError", "个人信息修改更新失败");
			return  INPUT;
		}
		}else{
			//不重名后进行后续操作
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
	                ts.rollback();       //事务回滚               
	            }  
	            e.printStackTrace();  
				addFieldError("updateError", "个人信息修改更新失败");
				return  INPUT;
			}
		}
	}
	
	//管理员修改用户信息
	//对个人信息进行更新
		public String aupdateProfile() throws Exception{
			//判断email是否重名
			UserDAO userDAO = new UserDAO();
			List<User> useList = userDAO.findAll();
			for (User a : useList) {
				if (userEmail.equals(a.getUserEmail())) {
					if (Long.parseLong(userId)!=a.getUserId()) {
						addFieldError("sameEmail", "该邮箱已经有人注册了！");
						return INPUT;
					}
				}
			}
			if(getUserHeadFileName()!=null){
				//完成图片的上传
				String fname = FileName.reName(getUserHeadFileName());
		    	try {
		    		FileInputStream fis = new FileInputStream(getUserHead().getAbsolutePath());  
		            FileOutputStream  fos=new FileOutputStream(ServletActionContext.getServletContext().
		            		getRealPath(getSavePath())+ "\\" + fname); 
		            File file = new File(getSavePath());
		            
		            //文件复制开始
		            if (!file.exists()) {
		    			file.mkdirs();
		    		}
		            
		            int len=0;  
		            while((len=fis.read())!=-1){  
		            	fos.write(len);
		            }  
		            fos.close();  
		            fis.close();  
		          //复制完成
				} catch (Exception e) {
					e.printStackTrace();
					addFieldError("copyError", "头像上传出错");
					return ERROR;
				}
			//不重名后进行后续操作
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
	                ts.rollback();       //事务回滚               
	            }  
	            e.printStackTrace();  
				addFieldError("aupdateError", "个人信息修改更新失败");
				return  INPUT;
			}
			}else{
				//不重名后进行后续操作
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
		                ts.rollback();       //事务回滚               
		            }  
		            e.printStackTrace();  
					addFieldError("aupdateError", "个人信息修改更新失败");
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
				addFieldError("searchError", "用户信息搜索失败");
				return "input";
			}
	    	
	    }
		@SuppressWarnings("unchecked")
		public String deleteProfile() throws Exception{
			UserDAO uDao = new UserDAO();//创建IDAo对象用于执行删除操作
			/*ImageDAO imgDao = new ImageDAO();	//创建imageDao用于删除相关图片
			PostsDAO postsDao = new PostsDAO();//创建PostsDao用于删除相关文章
*/			String uid =  ServletActionContext. getRequest().getParameter("uId");
			Long long1 = Long.parseLong(uid);
			try {
				User user = uDao.findById(long1);
				/*//获取用户对应的文章和图片
				List<Image> imgList = imgDao.findByUser(user);
				List<Posts> postList = postsDao.findByUser(user);*/
				uDao.delete(user);//删除用户
				//这算级联操作吗？？不清楚。。。
				/*for (Image image : imgList) {
					imgDao.delete(image);
				}
				for (Posts posts : postList) {
					postsDao.delete(posts);
				}*/
				return "deleteSuccess";
			} catch (Exception e) {
				addFieldError("deleteError", "删除信息失败！");
				return INPUT;
			}
		}
	
}
