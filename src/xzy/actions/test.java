package xzy.actions;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.HibernateSessionFactory;
import xzy.beans.Image;
import xzy.dao.ImageDAO;

public class test {

	public static void main(String[] args) {
	/*// ʵ����Configuration��
	Configuration conf = new Configuration()
			// ����������configure()����Ĭ�ϼ���hibernate.cfg.xml�ļ���
			// �������abc.xml��Ϊ���������ټ���hibernate.cfg.xml����Ϊ����abc.xml
			.configure();
	
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties())
			.build();
	// ��Configurationʵ������SessionFactoryʵ��
	
	SessionFactory sf = conf.buildSessionFactory();
	
	Session sess = sf.openSession();
	// ��ʼ����
	Transaction tx = sess.beginTransaction();
	Query query = sess.createQuery("from Posts");
    //ʵ�ַ�ҳ
    query.setFirstResult(0);
    query.setMaxResults(2);
    List<Posts> posts = query.list();
    for(Posts post : posts) {
        System.out.println(post.getPostContent());
    }
    
    tx.commit();*/
	/*	
		Session session = HibernateSessionFactory.getSession();
		Transaction ts = session.getTransaction();  
		try {
			ts.begin();
			User user = new User();
			user.setUserEmail("2@qq.com");
			user.setUserName("Tony");
			user.setUserPassword("2");
			session.save(user);
			ts.commit();  
			
		} catch (Exception e) {
			 e.printStackTrace();  
             ts.rollback();  
		}*/
		/*PostsDAO pd = new PostsDAO();
		Posts posts = new Posts();
		posts.setPostTitle("����1");
		posts.setPostContent("����ǲ�������");
		posts.setPostLabel("����");
		pd.save(posts);*/
		/*PostsDAO pd = new PostsDAO();
  		@SuppressWarnings("unchecked")
		List<Posts> postList = pd.findAll();
  		for (Posts posts : postList) {
			System.out.println(posts.getPostTitle());
		}
  		
  		Date date = new Date();
  		System.out.println(date);*/
		/*String a = "a.bcd";
		String type = a.substring(a.lastIndexOf("."));
        String fname = null;
        Date date = new Date();    
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddhhmmss");   
        fname=sf.format(date);
        fname+=(int)(Math.random()*100);//��2λ�����
        fname+=type;
        System.out.println(fname);*/
        
        //String abc = "adf,saf".split(",")[0];
		
		
		
	}

}
