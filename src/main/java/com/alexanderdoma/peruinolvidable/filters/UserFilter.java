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

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter { 
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String user_id = ((HttpServletRequest) request.getAttribute("user_id")).toString();
        if (user_id != null){
            chain.doFilter(request, response);
            return;
        }
        request.getRequestDispatcher("/login").forward(request, response);
    }

    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
        
    }
}
