package xzy.actions;

import java.sql.Date;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import xzy.beans.Comments;
import xzy.beans.Posts;
import xzy.dao.CommentsDAO;
import xzy.dao.PostsDAO;

public class CommentAction extends ActionSupport implements SessionAware{
	private String nickName;//������ǳ�
	private String commentContent;
	private String postId;	//���±��
	java.util.Date date = new java.util.Date();
	private Date postDate = new Date(date.getTime());	//��ȡ��������
	private Map<String, Object> session;
	
	
	
	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	public void validateSubmitComment() {
		//�����������ݹ���
		String[] badMessage = {"sb","ɵ��","��ȱ","ɵ��","����","����"};//�໰
		for (String a:badMessage) {
			if (commentContent.contains(a)) {
				addFieldError("zanghua","������������к��в����ַ�");
			}
		}

	}

	public String submitComment() throws Exception{
	
		try {
			//��ȡ����
			PostsDAO postsDAO = new PostsDAO();
			Posts posts = postsDAO.findById(Long.parseLong(postId));
			//��������
			Comments comments = new Comments();
			comments.setUserName(nickName);
			comments.setCommentContent(commentContent);
			comments.setCommentDate(postDate);
			comments.setPosts(posts);
			CommentsDAO cdao = new CommentsDAO();
			cdao.save(comments);
			
			return "submitSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("commentError", "���۴���");
			return ERROR;
		}
	}
	public String deleteComment() throws Exception{
		CommentsDAO cDao = new CommentsDAO();//����IDAo��������ִ��ɾ������
		String uid =  ServletActionContext. getRequest().getParameter("cId");
		Long long1 = Long.parseLong(uid);
		try {
			Comments comm = cDao.findById(long1);
			cDao.delete(comm);
			return "deleteSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("deleteError", "ɾ����Ϣʧ�ܣ�");
			return INPUT;
		}
	}
	
}
