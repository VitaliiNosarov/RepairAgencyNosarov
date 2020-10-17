package ua.kharkiv.nosarev.filter;

import org.junit.Before;
import org.junit.Test;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.*;

public class AuthorizationFilterTest {

    ServletContext mockContext;
    private Map<UserRole, Set<String>> uriMap;
    private Set<String> mockUriSet;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;
    private FilterChain mockChain;
    private FilterConfig mockConfig;
    private HttpSession mockSession;
    private AuthorizationFilter filter;
    private User user;

    public AuthorizationFilterTest() {
    }

    @Before
    public void init() {
        filter = new AuthorizationFilter();
        user = new User();
        user.setRole(UserRole.ADMIN);
        uriMap = mock(HashMap.class);
        mockUriSet = mock(HashSet.class);
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
        mockContext = mock(ServletContext.class);
        mockSession = mock(HttpSession.class);
        mockChain = mock(FilterChain.class);
        mockConfig = mock(FilterConfig.class);
    }

    @Test
    public void doFilterShouldPassRequestWithAllowablePath() throws IOException, ServletException {
        when(mockConfig.getServletContext()).thenReturn(mockContext);
        when(mockContext.getAttribute("uriMap")).thenReturn(uriMap);
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getServletPath()).thenReturn("/login");
        filter.doFilter(mockRequest, mockResponse, mockChain);
        verify(mockChain).doFilter(mockRequest, mockResponse);
    }

    @Test
    public void doFilterShouldBlockRequestWhenPassIsAbsentInUriSet() throws IOException, ServletException {
        when(mockConfig.getServletContext()).thenReturn(mockContext);
        when(mockContext.getAttribute("uriMap")).thenReturn(uriMap);
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getServletPath()).thenReturn("/orders");
        when(mockSession.getAttribute("user")).thenReturn(user);
        when(mockUriSet.contains("/orders")).thenReturn(false);
        when(uriMap.get(user.getRole())).thenReturn(mockUriSet);
        filter.init(mockConfig);
        filter.doFilter(mockRequest, mockResponse, mockChain);
        verify(mockChain, never()).doFilter(mockRequest, mockResponse);
    }

    @Test
    public void doFilterShouldPassRequestWhenPassIsPresentInUriSet() throws IOException, ServletException {
        when(mockConfig.getServletContext()).thenReturn(mockContext);
        when(mockContext.getAttribute("uriMap")).thenReturn(uriMap);
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getServletPath()).thenReturn("/orders");
        when(mockSession.getAttribute("user")).thenReturn(user);
        when(mockUriSet.contains("/orders")).thenReturn(true);
        when(uriMap.get(user.getRole())).thenReturn(mockUriSet);
        filter.init(mockConfig);
        filter.doFilter(mockRequest, mockResponse, mockChain);
        verify(mockChain).doFilter(mockRequest, mockResponse);
    }

    @Test
    public void doFilterShouldRedirectRespWhenPassIsAbsentInUriSet() throws IOException, ServletException {
        when(mockConfig.getServletContext()).thenReturn(mockContext);
        when(mockContext.getAttribute("uriMap")).thenReturn(uriMap);
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getServletPath()).thenReturn("/orders");
        when(mockSession.getAttribute("user")).thenReturn(user);
        when(mockUriSet.contains("/orders")).thenReturn(false);
        when(uriMap.get(user.getRole())).thenReturn(mockUriSet);
        filter.init(mockConfig);
        filter.doFilter(mockRequest, mockResponse, mockChain);
        verify(mockResponse).sendRedirect(anyString());
    }
    @Test
    public void doFilterShouldRedirectRespWhenUserIsNull() throws IOException, ServletException {
        when(mockConfig.getServletContext()).thenReturn(mockContext);
        when(mockContext.getAttribute("uriMap")).thenReturn(uriMap);
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getServletPath()).thenReturn("/orders");
        when(mockSession.getAttribute("user")).thenReturn(null);
        filter.doFilter(mockRequest, mockResponse, mockChain);
        verify(mockResponse).sendRedirect(anyString());
    }
}