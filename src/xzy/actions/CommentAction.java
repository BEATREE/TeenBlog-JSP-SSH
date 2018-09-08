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
	private String nickName;//浏览者昵称
	private String commentContent;
	private String postId;	//文章编号
	java.util.Date date = new java.util.Date();
	private Date postDate = new Date(date.getTime());	//获取发表日期
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
		//进行评论内容过滤
		String[] badMessage = {"sb","傻逼","二缺","傻子","他妈","尼玛"};//脏话
		for (String a:badMessage) {
			if (commentContent.contains(a)) {
				addFieldError("zanghua","您输入的文字中含有不良字符");
			}
		}

	}

	public String submitComment() throws Exception{
	
		try {
			//获取文章
			PostsDAO postsDAO = new PostsDAO();
			Posts posts = postsDAO.findById(Long.parseLong(postId));
			//创建评论
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
			addFieldError("commentError", "评论错误");
			return ERROR;
		}
	}
	public String deleteComment() throws Exception{
		CommentsDAO cDao = new CommentsDAO();//创建IDAo对象用于执行删除操作
		String uid =  ServletActionContext. getRequest().getParameter("cId");
		Long long1 = Long.parseLong(uid);
		try {
			Comments comm = cDao.findById(long1);
			cDao.delete(comm);
			return "deleteSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("deleteError", "删除信息失败！");
			return INPUT;
		}
	}
	
}
