package br.tsi.comichub.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import br.tsi.comichub.enums.UserRole;
import br.tsi.comichub.model.Account;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "*.xhtml")
public class AuthorizationFilter extends HttpFilter implements Filter {

    private static final List<String> PUBLIC_PAGES = Arrays.asList(
    		"/login.xhtml", "/signup.xhtml", "/verify.xhtml", "/error.xhtml", "/templates/main-template.xhtml");

    private static final List<String> USER_PAGES = Arrays.asList(
            "/boxes.xhtml", "/comics.xhtml", "/loans.xhtml", "/templates/navigation-template.xhtml");

    private static final List<String> ADMIN_PAGES = Arrays.asList(
            "/users.xhtml", "/late-loans.xhtml", "/report.xhtml");

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        String requestPath = request.getRequestURI();
        String contextPath = request.getContextPath();
        String pagePath = requestPath.substring(contextPath.length());

        // Recursos
        if (pagePath.startsWith("/jakarta.faces.resource/") || pagePath.startsWith("/resources/") || pagePath.contains("/WEB-INF/") || PUBLIC_PAGES.contains(pagePath)) {
            chain.doFilter(request, response);
            return;
        }

        Account authUser = null;
        if (session != null) {
            authUser = (Account) session.getAttribute("authUser");
        }

        // Verifica se tem um usuário logado, se não tiver, manda para pag de login
        if (authUser == null || authUser.getId() == null) {
            response.sendRedirect(contextPath + "/login.xhtml");
            return;
        }

        // Páginas de ADMIN
        if (ADMIN_PAGES.contains(pagePath)) {
        	
            if (authUser.getRole() == UserRole.ADMIN) 
            	chain.doFilter(request, response);
            else
            	response.sendRedirect(contextPath + "/error.xhtml");
            
            return;
        }

        if(!USER_PAGES.contains(pagePath)) 
        	response.sendRedirect(contextPath + "/error.xhtml");
        else 
        	chain.doFilter(request, response);
        
        return;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void destroy() {}
}
