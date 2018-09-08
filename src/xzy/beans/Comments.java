package xzy.beans;

import java.sql.Date;

/**
 * Comments entity. @author MyEclipse Persistence Tools
 */

public class Comments implements java.io.Serializable {

	// Fields

	private Long commentId;
	private Posts posts;
	private String commentContent;
	private Date commentDate;
	private String userName;

	// Constructors

	/** default constructor */
	public Comments() {
	}

	/** minimal constructor */
	public Comments(Posts posts, String commentContent, String userName) {
		this.posts = posts;
		this.commentContent = commentContent;
		this.userName = userName;
	}

	/** full constructor */
	public Comments(Posts posts, String commentContent, Date commentDate, String userName) {
		this.posts = posts;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.userName = userName;
	}

	// Property accessors

	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Posts getPosts() {
		return this.posts;
	}

	public void setPosts(Posts posts) {
		this.posts = posts;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}