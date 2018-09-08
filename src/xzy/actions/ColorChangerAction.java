package xzy.actions;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import xzy.beans.Color;

@SuppressWarnings("serial")
public class ColorChangerAction extends ActionSupport implements ServletContextAware{
	
	private Color color;
	ServletContext application = getApplication();
	
	//生成color的set和get方法
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public ServletContext getApplication() {
		return application;
	}
	
	public void setApplication(ServletContext application) {
		this.application = application;
	}
	
	@Override
	public void setServletContext(ServletContext application) {
		this.application = application;
	}
	
	@Override
	public String execute() throws Exception {
		application.setAttribute("lastColor", color);
		return SUCCESS;
	}
}
