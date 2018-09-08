package xzy.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import hibernate.HibernateSessionFactory;
import xzy.beans.Posts;
import xzy.beans.User;
import xzy.dao.ImageDAO;
import xzy.dao.PostsDAO;
import xzy.utils.FileName;
import xzy.utils.SimplifyText;

public class PostAction extends ActionSupport implements SessionAware,RequestAware{
	
	private String postTitle,postParagraphs,labels,lessParagraphs,postid;
	private SimplifyText simplifyText = new SimplifyText();
	java.util.Date date = new java.util.Date();
	private Date postDate = new Date(date.getTime());	//��ȡ��������
	private Map<String, Object> session,request; 
	//��־ͼƬ���ϴ��ͱ���
	private File featuredPicture;
	private String featuredPictureFileName,featuredPictureContentType;
	private String savePath;
	private String allowType;	//���������
	/*//����Ա�޸�����
	private String userid;
	
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}*/

	public String getPostid() {
		return postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostParagraphs() {
		return postParagraphs;
	}

	public void setPostParagraphs(String postParagraphs) {
		this.postParagraphs = postParagraphs;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public File getFeaturedPicture() {
		return featuredPicture;
	}

	public void setFeaturedPicture(File featuredPicture) {
		this.featuredPicture = featuredPicture;
	}

	public String getFeaturedPictureFileName() {
		return featuredPictureFileName;
	}

	public void setFeaturedPictureFileName(String featuredPictureFileName) {
		this.featuredPictureFileName = featuredPictureFileName;
	}

	public String getFeaturedPictureContentType() {
		return featuredPictureContentType;
	}

	public void setFeaturedPictureContentType(String featuredPictureContentType) {
		this.featuredPictureContentType = featuredPictureContentType;
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
	
	public Map<String, Object> getRequest() {
		return request;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		request = arg0;
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
			
		  if (file.length()<10971520) {//С��10M
			return true;
		}
	       
        }
        return false;
    }  
	//�Զ���������
    public void  validatePublishPost(){  	
    	//��������ͼƬ�ĸ�ʽ
        boolean b=check(featuredPictureContentType); 
        boolean a= checkSize(featuredPicture);
        if(!b){  
            addFieldError("featuredPictureUpload","���±�־ͼƬ�ļ���ʽ����");  
        }
        if (!a) {
			addFieldError("featurePictureUpload", "ͼƬ��С������Χ");
		}
    }
  
    //�޸�����
    public String updatePost() throws Exception{
    	
		Session sess = HibernateSessionFactory.getSession();
		Transaction ts = sess.beginTransaction();
		try {
			Posts posts = sess.get(Posts.class,Long.parseLong(postid) );
			posts.setPostTitle(postTitle);
			posts.setPostDate(postDate);
			posts.setPostLabel(labels);
			posts.setPostContent(postParagraphs);
			posts.setPostLesscontent(simplifyText.StripHTML(getPostParagraphs()));
			System.out.println(postParagraphs);
			sess.update(posts);
			ts.commit();
			sess.flush();
			session.remove("currentPostList");
			sess.close();
			return "updateSuccess";
		} catch (Exception e) {
			  
            e.printStackTrace();  
			addFieldError("updateError", "�����޸ĸ���ʧ��");
			return  INPUT;
		}
    }
	//ɾ������
    public String deletePost() throws Exception{
		PostsDAO pd = new PostsDAO();
		String pid =  ServletActionContext. getRequest().getParameter("pId");
		Long long1 = Long.parseLong(pid);
		User currentUser = (User)session.get("currentUser");	//��ȡ��ǰ�û�
		
		try {
			Posts posts = pd.findById(long1);
			pd.delete(posts);//ɾ�����
			//ͬ���û�
			Session sess = HibernateSessionFactory.getSession();
			Transaction ts = sess.beginTransaction();
			currentUser.setPostNumber(currentUser.getPostNumber()-1);
			sess.update(currentUser);
			ts.commit();
			session.remove("postResults");
			return "deleteSuccess";
		} catch (Exception e) {
			addFieldError("deleteError", "ɾ����Ϣʧ�ܣ�");
			return INPUT;
		}
	
	}
    public String adeletePost() throws Exception{
		PostsDAO pd = new PostsDAO();
		String pid =  ServletActionContext. getRequest().getParameter("pId");
		Long long1 = Long.parseLong(pid);
		try {
			Posts posts = pd.findById(long1);
			pd.delete(posts);
			posts.getUser().setPostNumber(posts.getUser().getPostNumber()-1);
			return "adeleteSuccess";
		} catch (Exception e) {
			addFieldError("deleteError", "ɾ����Ϣʧ�ܣ�");
			return INPUT;
		}
	
	}
    //��������
    @SuppressWarnings("unchecked")
	public String searchPost() throws Exception{
    	List<Posts> postlist = new ArrayList<Posts>();
    	try {
    		//��ȡ��ǰ�б��ӵ�ǰ�б��н�������
    		List<Posts> nowlist = (List<Posts>) session.get("currentPostList");
    		for (Posts posts : nowlist) {
				if (posts.getPostTitle().toLowerCase().contains(postTitle.toLowerCase())) {
					postlist.add(posts);
				}
			}
    		session.put("postResults", postlist);
        	return "searchSuccess";
		} catch (Exception e) {
			addFieldError("searchError", "��������ʧ��");
			return "input";
		}
    	
    }
    //��������
    @SuppressWarnings("unchecked")
	public String findPost() throws Exception{
    	List<Posts> postlist = new ArrayList<Posts>();
    	try {
    		//��ȡ��ǰ�б��ӵ�ǰ�б��н�������
    		List<Posts> nowlist = (List<Posts>) session.get("postList");
    		for (Posts posts : nowlist) {
				if (posts.getPostTitle().toLowerCase().contains(postTitle.toLowerCase())) {
					postlist.add(posts);
				}
			}
    		session.put("searchResults", postlist);
        	return "findSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("searchError", "��������ʧ��");
			return "input";
		}
    	
    }
    @SuppressWarnings("unchecked")
	public String asearchPost() throws Exception{
    	List<Posts> postlist = new ArrayList<Posts>();
    	try {
    		//��ȡ��ǰ�б��ӵ�ǰ�б��н�������
    		List<Posts> nowlist = (List<Posts>) session.get("allPostList");
    		for (Posts posts : nowlist) {
				if (posts.getPostTitle().toLowerCase().contains(postTitle.toLowerCase())) {
					postlist.add(posts);
				}
			}
    		session.put("postResults", postlist);
        	return "asearchSuccess";
		} catch (Exception e) {
			addFieldError("searchError", "��������ʧ��");
			return "input";
		}
    	
    }
	//������ҳԤ���صķ���
	public String showPost() throws Exception{
		try {
			Session session = HibernateSessionFactory.getSession();
			@SuppressWarnings("unused")
			Transaction ts = session.beginTransaction();
			Query query = session.createQuery("from Posts");
			@SuppressWarnings("unchecked")
			List<Posts> postList = query.list();
			this.session.put("postList", postList);
			return "postList";
		} catch (Exception e) {
			return ERROR;
		}
		
	}
	
	public String publishPost() throws Exception{
		Session sess = HibernateSessionFactory.getSession();
		User currentUser = (User)session.get("currentUser");	//��ȡ��ǰ�û�
		// ��ʼ����
		Transaction tx = sess.beginTransaction();
		lessParagraphs = simplifyText.StripHTML(getPostParagraphs());
		
		//���ͼƬ���ϴ�
		String fname = FileName.reName(getFeaturedPictureFileName());
    	try {
    		FileInputStream fis = new FileInputStream(getFeaturedPicture().getAbsolutePath());  
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
			addFieldError("copyError", "ͼƬ�ϴ�����");
			return ERROR;
		}
        
		
		try {
			Posts posts = new Posts();
			posts.setPostTitle(postTitle);
			posts.setPostContent(postParagraphs);
			posts.setPostLesscontent(lessParagraphs);
			posts.setPostDate(postDate);
			posts.setPostLabel(labels);
			posts.setPostImage(getSavePath()+"\\"+fname);//�洢����·��
			posts.setUser(currentUser);
			posts.setPostLikes(0);
			currentUser.setPostNumber(currentUser.getPostNumber()+1);
			sess.update(currentUser);
			sess.save(posts);
			// �ύ����
			tx.commit();
			// �ر�Session
			sess.close();
			
			return "publishSuccess";
		} catch (Exception e) {
			addFieldError("publishError", "���´������ݿ����");
		}
		return ERROR;
	}
	
	//Ϊ���µ���
	public String likePost() throws Exception {
		
		//��ȡ���ӵĲ���
		String pid =  ServletActionContext. getRequest().getParameter("pId");
		Long long1 = Long.parseLong(pid);
		Session sess = HibernateSessionFactory.getSession();
		Transaction transaction = sess.beginTransaction();
		try {
			Posts posts = sess.get(Posts.class, long1);
			posts.setPostLikes(posts.getPostLikes()+1);
			sess.update(posts);
			transaction.commit();
			sess.clear();
			sess.close();
			return "likeSuccess";
		} catch (Exception e) {
			addFieldError("likeError", "�ܱ�Ǹ����û�гɹ�~");
			return INPUT;
		}
			
		
	}
	
	@Override
	public String execute() throws Exception {
		
		return super.execute();
	}


	
	
	
	
	
}
