package xzy.beans;

/**
 * Image entity. @author MyEclipse Persistence Tools
 */

public class Image implements java.io.Serializable {

	// Fields

	private Long imageId;
	private User user;
	private String imageName;
	private String imageDesc;
	private String imageSrc;

	// Constructors

	/** default constructor */
	public Image() {
	}

	/** minimal constructor */
	public Image(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	/** full constructor */
	public Image(User user, String imageName, String imageDesc, String imageSrc) {
		this.user = user;
		this.imageName = imageName;
		this.imageDesc = imageDesc;
		this.imageSrc = imageSrc;
	}

	// Property accessors

	public Long getImageId() {
		return this.imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getImageName() {
		return this.imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageDesc() {
		return this.imageDesc;
	}

	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}

	public String getImageSrc() {
		return this.imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

}