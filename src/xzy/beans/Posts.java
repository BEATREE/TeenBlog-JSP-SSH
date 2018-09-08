package xzy.beans;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Posts entity. @author MyEclipse Persistence Tools
 */

public class Posts implements java.io.Serializable {

	// Fields

	private Long postId;
	private User user;
	private String postTitle;
	private String postContent;
	private String postLabel;
	private String postImage;
	private Date postDate;
	private Integer postLikes;
	private String postLesscontent;
	private Set commentses = new HashSet(0);
	private Set commentses_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public Posts() {
	}

	/** minimal constructor */
	public Posts(String postTitle, String postContent) {
		this.postTitle = postTitle;
		this.postContent = postContent;
	}

	/** full constructor */
	public Posts(User user, String postTitle, String postContent, String postLabel, String postImage, Date postDate,
			Integer postLikes, String postLesscontent, Set commentses, Set commentses_1) {
		this.user = user;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postLabel = postLabel;
		this.postImage = postImage;
		this.postDate = postDate;
		this.postLikes = postLikes;
		this.postLesscontent = postLesscontent;
		this.commentses = commentses;
		this.commentses_1 = commentses_1;
	}

	// Property accessors

	public Long getPostId() {
		return this.postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPostTitle() {
		return this.postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return this.postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostLabel() {
		return this.postLabel;
	}

	public void setPostLabel(String postLabel) {
		this.postLabel = postLabel;
	}

	public String getPostImage() {
		return this.postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Integer getPostLikes() {
		return this.postLikes;
	}

	public void setPostLikes(Integer postLikes) {
		this.postLikes = postLikes;
	}

	public String getPostLesscontent() {
		return this.postLesscontent;
	}

	public void setPostLesscontent(String postLesscontent) {
		this.postLesscontent = postLesscontent;
	}

	public Set getCommentses() {
		return this.commentses;
	}

	public void setCommentses(Set commentses) {
		this.commentses = commentses;
	}

	public Set getCommentses_1() {
		return this.commentses_1;
	}

	public void setCommentses_1(Set commentses_1) {
		this.commentses_1 = commentses_1;
	}

}