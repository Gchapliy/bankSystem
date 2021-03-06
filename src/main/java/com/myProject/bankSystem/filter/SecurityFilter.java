package com.myProject.bankSystem.filter;

import com.myProject.bankSystem.bean.userAccount.Role;
import com.myProject.bankSystem.bean.userAccount.UserAccount;
import com.myProject.bankSystem.utils.AppUtils;
import com.myProject.bankSystem.utils.LocaleUtils;
import com.myProject.bankSystem.utils.SecurityUtils;
import com.myProject.bankSystem.utils.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();

        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());

        boolean isError = true;
        boolean isException = false;
        boolean isAccessDenied = true;

        if(servletPath.equals("/login")){
            filterChain.doFilter(request, response);
            return;
        }

        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            // User Email
            String userEmail = loginedUser.getUserAccountEmail();

            // Roles
            Role role = loginedUser.getUserAccountRole();

            // Wrap old request by a new Request with userName and Role information.
            wrapRequest = new UserRoleRequestWrapper(userEmail, role, request);
        }

        // Pages must be signed in.
        if (SecurityUtils.isSecurityPage(request)) {

            // If the user is not logged in,
            // Redirect to the login page.
            if (loginedUser == null) {

                String requestUri = request.getRequestURI();

                // Store the current page to redirect to after successful login.
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            // Check if the user has a valid role?
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                    request.getSession().invalidate();
                    AppUtils.deleteUserCookie(response);

                LocaleUtils.setLocaleError(request, isError, isException, isAccessDenied);
                request.getRequestDispatcher("templates/accessDeniedView.jsp").forward(request, response);
                return;
            }
        }

        filterChain.doFilter(wrapRequest, response);
    }

    @Override
    public void destroy() {

    }
}
