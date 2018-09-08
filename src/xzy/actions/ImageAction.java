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
import xzy.utils.FileName;

public class ImageAction extends ActionSupport implements SessionAware{
	//表单上提供的字段
	private File image;
	private String imageFileName,imagename;
	private String imageContentType;//上传文件的MIME类型。上传字段名称+ContentType 注意大小写
	private String desc;
	private String savePath;
	private String allowType;	//允许的类型
	private Map<String, Object> session;
	private String imageid,imagesrc;	//用于修改时候使用的参数
	
	
	
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	
	
	public String getImagesrc() {
		return imagesrc;
	}
	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
		
	}
	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}


	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	
	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	
	public boolean check(String type){  	//对上传类型进行检验
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
	
    public void  validateUploadImage(){  				//自定义拦截器
        boolean b=check(imageContentType);  
        boolean a = checkSize(image);
        
        if(!b){  
            addFieldError("imageUpload","图片文件格式错误");  
        }
        if (!a) {
			addFieldError("imageSize", "图片大小超出范围");
		}
    }  
    //修改图片信息
    public String updateImage() throws Exception{
    	Session sess = HibernateSessionFactory.getSession();
		Transaction ts = sess.beginTransaction();
		
		try {
			System.out.println("action中的1:imageid:"+imageid);
			//获取主键对应的图片对象
			Image image = sess.get(Image.class,Long.parseLong(getImageid()));
			image.setImageName(imagename);
			image.setImageDesc(desc);
			image.setImageSrc(imagesrc);
			System.out.println("action中的2:imageid:"+imageid);
			ts.commit();
			sess.clear();
			session.remove("currentImageList");
			System.out.println("action中的3:imageid:"+imageid);
			sess.refresh(image);
			sess.close();
			return "updateSuccess";
		} catch (Exception e) {
			if(ts!=null){  
                ts.rollback();       //事务回滚               
            }  
            e.printStackTrace();  
			addFieldError("updateError", "图片信息修改更新失败");
			return  INPUT;
		}
    }
    //删除图片信息
    public String deleteImage() throws Exception{
    	ImageDAO iDao = new ImageDAO();//创建IDAo对象用于执行删除操作
		String pid =  ServletActionContext. getRequest().getParameter("iId");
		Long long1 = Long.parseLong(pid);
		try {
			Image image = iDao.findById(long1);
			iDao.delete(image);
			session.remove("pictureResults");
			return "deleteSuccess";
		} catch (Exception e) {
			addFieldError("deleteError", "删除信息失败！");
			return INPUT;
		}
    }
    //删除图片信息
    public String adeleteImage() throws Exception{
    	ImageDAO iDao = new ImageDAO();	//创建IDAo对象用于执行删除操作
		String pid =  ServletActionContext. getRequest().getParameter("iId");
		Long long1 = Long.parseLong(pid);
		try {
			Image image = iDao.findById(long1);
			iDao.delete(image);
			session.remove("pictureResults");
			return "adeleteSuccess";
		} catch (Exception e) {
			addFieldError("deleteError", "删除信息失败！");
			return INPUT;
		}
    }
    //查询图片
    public String searchImage() throws Exception{
    	List<Image> imagelist = new ArrayList<Image>();
    	try {
    		//获取当前列表，从当前列表中进行搜索
    		List<Image> nowlist = (List<Image>) session.get("currentImageList");
    		for (Image images : nowlist) {
				if (images.getImageName().contains(imagename)) {
					imagelist.add(images);
				}
			}
    		session.put("pictureResults", imagelist);
        	return "searchSuccess";
		} catch (Exception e) {
			addFieldError("searchError", "图片搜索失败");
			return "input";
		}
    }
    
    @SuppressWarnings("unchecked")
	public String asearchImage() throws Exception{
    	List<Image> imageList = new ArrayList<Image>();
    	try {
    		//获取当前列表，从当前列表中进行搜索
    		List<Image> nowlist = (List<Image>) session.get("allImageList");
    		for (Image image : nowlist) {
				if (image.getImageName().contains(imagename)) {
					imageList.add(image);
				}
			}
    		session.put("pictureResults", imageList);
        	return "asearchSuccess";
		} catch (Exception e) {
			addFieldError("searchError", "图片搜索失败");
			return "input";
		}
    	
    }
    //上传图片
    public String uploadImage() throws Exception{
    	
    	String fname = FileName.reName(getImageFileName());
    	FileInputStream fis = new FileInputStream(getImage().getAbsolutePath());  
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
        //数据库操作开始
        Image image = new Image();
        ImageDAO idao = new ImageDAO();
        User currentUser = (User)session.get("currentUser");	//获取当前用户
        image.setImageName(getImagename());
        image.setImageSrc(getSavePath()+"\\"+fname);
        image.setImageDesc(getDesc());
        image.setUser(currentUser);
        
        idao.save(image);
        return "uploadSuccess";  
    }
    
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	
	
	
	
}
