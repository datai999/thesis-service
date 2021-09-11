package com.thesis.service.config.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.thesis.service.service.user.UserService;
import com.thesis.service.utils.ContextAccessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FirebaseFilter extends OncePerRequestFilter {

  private static final String HEADER_NAME = "X-auth";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String xAuth = request.getHeader(HEADER_NAME);

    try {
      if (StringUtils.isBlank(xAuth)) {
        throw new BadCredentialsException("Token must not be blank");
      }

      FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(xAuth);

      var auth = ContextAccessor.getBean(UserService.class).getAuthentication(firebaseToken);
      SecurityContextHolder.getContext().setAuthentication(auth);

    } catch (Exception e) {
      this.authorException(request, response, e.getMessage());
    }

    filterChain.doFilter(request, response);
  }

  private void authorException(
      HttpServletRequest request,
      HttpServletResponse response,
      String message) {
    log.error("Token Firebase Exception : {}", message);
    SecurityContextHolder.clearContext();
    ContextAccessor.getBean(HandlerExceptionResolver.class, "handlerExceptionResolver")
        .resolveException(request, response, null, new BadCredentialsException(message));
  }

}
