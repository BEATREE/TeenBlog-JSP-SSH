package xzy.beans;

import java.util.HashSet;
import java.util.Set;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private Long userId;
     private String userEmail;
     private String userHead;
     private String userName;
     private String userPassword;
     private Long postNumber;
     private Set images = new HashSet(0);
     private Set images_1 = new HashSet(0);
     private Set postses = new HashSet(0);
     private Set postses_1 = new HashSet(0);


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(String userEmail, String userName, String userPassword) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
    }
    
    /** full constructor */
    public User(String userEmail, String userHead, String userName, String userPassword, Long postNumber, Set images, Set images_1, Set postses, Set postses_1) {
        this.userEmail = userEmail;
        this.userHead = userHead;
        this.userName = userName;
        this.userPassword = userPassword;
        this.postNumber = postNumber;
        this.images = images;
        this.images_1 = images_1;
        this.postses = postses;
        this.postses_1 = postses_1;
    }

   
    // Property accessors

    public Long getUserId() {
        return this.userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return this.userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserHead() {
        return this.userHead;
    }
    
    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Long getPostNumber() {
        return this.postNumber;
    }
    
    public void setPostNumber(Long postNumber) {
        this.postNumber = postNumber;
    }

    public Set getImages() {
        return this.images;
    }
    
    public void setImages(Set images) {
        this.images = images;
    }

    public Set getImages_1() {
        return this.images_1;
    }
    
    public void setImages_1(Set images_1) {
        this.images_1 = images_1;
    }

    public Set getPostses() {
        return this.postses;
    }
    
    public void setPostses(Set postses) {
        this.postses = postses;
    }

    public Set getPostses_1() {
        return this.postses_1;
    }
    
    public void setPostses_1(Set postses_1) {
        this.postses_1 = postses_1;
    }
   








}