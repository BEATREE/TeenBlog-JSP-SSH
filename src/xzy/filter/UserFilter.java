package xzy.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain chain) throws IOException, ServletException {
		servletRequest.setCharacterEncoding("utf-8");
		servletResponse.setContentType("text/html;charset=utf-8");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;    
	    HttpServletResponse response = (HttpServletResponse) servletResponse;    
	    HttpSession session = request.getSession(); 
	    
	    if (session.getAttribute("currentUser")==null) {
	    	PrintWriter out = servletResponse.getWriter();
            out.println("<script language=javascript>");
            out.println("alert('Äú»¹Ã»ÓÐµÇÂ¼');");
            out.println("window.history.back(-1);");
            out.println("</script>");
		}else {
            chain.doFilter(servletRequest, servletResponse);
	    }

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
