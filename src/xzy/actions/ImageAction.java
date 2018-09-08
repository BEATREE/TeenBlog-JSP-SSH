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
	//�����ṩ���ֶ�
	private File image;
	private String imageFileName,imagename;
	private String imageContentType;//�ϴ��ļ���MIME���͡��ϴ��ֶ�����+ContentType ע���Сд
	private String desc;
	private String savePath;
	private String allowType;	//���������
	private Map<String, Object> session;
	private String imageid,imagesrc;	//�����޸�ʱ��ʹ�õĲ���
	
	
	
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

	
	public boolean check(String type){  	//���ϴ����ͽ��м���
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
	
    public void  validateUploadImage(){  				//�Զ���������
        boolean b=check(imageContentType);  
        boolean a = checkSize(image);
        
        if(!b){  
            addFieldError("imageUpload","ͼƬ�ļ���ʽ����");  
        }
        if (!a) {
			addFieldError("imageSize", "ͼƬ��С������Χ");
		}
    }  
    //�޸�ͼƬ��Ϣ
    public String updateImage() throws Exception{
    	Session sess = HibernateSessionFactory.getSession();
		Transaction ts = sess.beginTransaction();
		
		try {
			System.out.println("action�е�1:imageid:"+imageid);
			//��ȡ������Ӧ��ͼƬ����
			Image image = sess.get(Image.class,Long.parseLong(getImageid()));
			image.setImageName(imagename);
			image.setImageDesc(desc);
			image.setImageSrc(imagesrc);
			System.out.println("action�е�2:imageid:"+imageid);
			ts.commit();
			sess.clear();
			session.remove("currentImageList");
			System.out.println("action�е�3:imageid:"+imageid);
			sess.refresh(image);
			sess.close();
			return "updateSuccess";
		} catch (Exception e) {
			if(ts!=null){  
                ts.rollback();       //����ع�               
            }  
            e.printStackTrace();  
			addFieldError("updateError", "ͼƬ��Ϣ�޸ĸ���ʧ��");
			return  INPUT;
		}
    }
    //ɾ��ͼƬ��Ϣ
    public String deleteImage() throws Exception{
    	ImageDAO iDao = new ImageDAO();//����IDAo��������ִ��ɾ������
		String pid =  ServletActionContext. getRequest().getParameter("iId");
		Long long1 = Long.parseLong(pid);
		try {
			Image image = iDao.findById(long1);
			iDao.delete(image);
			session.remove("pictureResults");
			return "deleteSuccess";
		} catch (Exception e) {
			addFieldError("deleteError", "ɾ����Ϣʧ�ܣ�");
			return INPUT;
		}
    }
    //ɾ��ͼƬ��Ϣ
    public String adeleteImage() throws Exception{
    	ImageDAO iDao = new ImageDAO();	//����IDAo��������ִ��ɾ������
		String pid =  ServletActionContext. getRequest().getParameter("iId");
		Long long1 = Long.parseLong(pid);
		try {
			Image image = iDao.findById(long1);
			iDao.delete(image);
			session.remove("pictureResults");
			return "adeleteSuccess";
		} catch (Exception e) {
			addFieldError("deleteError", "ɾ����Ϣʧ�ܣ�");
			return INPUT;
		}
    }
    //��ѯͼƬ
    public String searchImage() throws Exception{
    	List<Image> imagelist = new ArrayList<Image>();
    	try {
    		//��ȡ��ǰ�б��ӵ�ǰ�б��н�������
    		List<Image> nowlist = (List<Image>) session.get("currentImageList");
    		for (Image images : nowlist) {
				if (images.getImageName().contains(imagename)) {
					imagelist.add(images);
				}
			}
    		session.put("pictureResults", imagelist);
        	return "searchSuccess";
		} catch (Exception e) {
			addFieldError("searchError", "ͼƬ����ʧ��");
			return "input";
		}
    }
    
    @SuppressWarnings("unchecked")
	public String asearchImage() throws Exception{
    	List<Image> imageList = new ArrayList<Image>();
    	try {
    		//��ȡ��ǰ�б��ӵ�ǰ�б��н�������
    		List<Image> nowlist = (List<Image>) session.get("allImageList");
    		for (Image image : nowlist) {
				if (image.getImageName().contains(imagename)) {
					imageList.add(image);
				}
			}
    		session.put("pictureResults", imageList);
        	return "asearchSuccess";
		} catch (Exception e) {
			addFieldError("searchError", "ͼƬ����ʧ��");
			return "input";
		}
    	
    }
    //�ϴ�ͼƬ
    public String uploadImage() throws Exception{
    	
    	String fname = FileName.reName(getImageFileName());
    	FileInputStream fis = new FileInputStream(getImage().getAbsolutePath());  
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
        //���ݿ������ʼ
        Image image = new Image();
        ImageDAO idao = new ImageDAO();
        User currentUser = (User)session.get("currentUser");	//��ȡ��ǰ�û�
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
