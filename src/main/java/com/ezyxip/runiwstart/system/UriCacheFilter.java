package com.ezyxip.runiwstart.system;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class UriCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if(httpRequest.getRequestURI().matches("VAADIN/")){
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = httpRequest.getSession();
        UriCacheStore store = (UriCacheStore) session.getAttribute("uriCache");
        if(store == null){
            session.setAttribute("uriCache", new UriCacheStore(3){{putUri(httpRequest.getRequestURI());}});
        } else {
            if(!store.getCache().lastElement().equals(httpRequest.getRequestURI()))
                store.putUri(httpRequest.getRequestURI());
        }
        chain.doFilter(request, response);
    }
}
