package com.alexanderdoma.peruinolvidable.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpSession session = ((HttpServletRequest) request).getSession();
        if(session.getAttribute("admin_id") != null){
            chain.doFilter(request, response);
        }else {
            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/admin");
        }
    }

    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        
    }
}
