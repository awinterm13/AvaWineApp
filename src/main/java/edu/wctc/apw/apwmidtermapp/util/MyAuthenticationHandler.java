/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.util;
import edu.wctc.apw.apwmidtermapp.exception.ParameterMissingException;
import java.io.IOException;import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;


/**
 * This Class provides a method to let Spring direct the user to a specific page defined by user role.
 * 
 * @author andre_000
 */

public class MyAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {
        private final String USER_ROLE = "ROLE_USER";
        private final String USER_URL = "/userLandingPage.jsp";
        
        private final String ADMIN_ROLE = "ROLE_MGR";
        private final String ADMIN_URL = "/mgrLandingPage.jsp";
       
        
        /**
         * Directs the user to a role determined landing page after logging in successfully.
         * 
         * @param request
         * @param response
         * @param authentication
         * @throws ServletException
         * @throws IOException 
         */
        @Override        
        public void onAuthenticationSuccess(HttpServletRequest request,
                HttpServletResponse response, Authentication authentication)
                throws ServletException, IOException {
            if(request == null || response == null || authentication == null){
                throw new ParameterMissingException();
            }
//            final String USER_URL =  "/userLandingPage.jsp";
//            final String ADMIN_URL = "/mgrLandingPage.jsp";        
            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities()); 
            if (roles.contains(ADMIN_ROLE)) {            
                getRedirectStrategy().sendRedirect(request, response,ADMIN_URL);
            } else if (roles.contains(USER_ROLE)) {   
                getRedirectStrategy().sendRedirect(request, response,USER_URL);
            } else {            
                super.onAuthenticationSuccess(request, response, authentication);            
                return;        
            }    
        }}

