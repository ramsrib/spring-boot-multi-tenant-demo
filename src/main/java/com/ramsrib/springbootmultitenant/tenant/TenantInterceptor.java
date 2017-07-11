package com.ramsrib.springbootmultitenant.tenant;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

  private static final String TENANT_HEADER = "X-TenantID";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String tenantHeader = request.getHeader(TENANT_HEADER);
    boolean tenantSet = false;
    if(tenantHeader != null && !tenantHeader.isEmpty()) {
      TenantContext.setCurrentTenant(tenantHeader);
      tenantSet = true;
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.getWriter().write("{\"error\": \"No tenant supplied\"}");
      response.getWriter().flush();
    }
    return tenantSet;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    TenantContext.clear();
  }

}
