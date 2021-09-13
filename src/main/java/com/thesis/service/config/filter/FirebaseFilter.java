package com.thesis.service.config.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
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

    System.out.println("\nNew request");

    String xAuth = request.getHeader(HEADER_NAME);

    try {
      if (StringUtils.isBlank(xAuth)) {
        throw new BadCredentialsException("Token must not be blank");
      }

      // TODO: remove back door
      if (xAuth.length() < 50) {
        var auth = ContextAccessor.getBean(UserService.class).getAuthentication(xAuth);
        SecurityContextHolder.getContext().setAuthentication(auth);

        log.info("Request user >>> {}", auth.getPrincipal().getEmail());
        filterChain.doFilter(request, response);
        return;
      }

      FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(xAuth);

      var auth = ContextAccessor.getBean(UserService.class).getAuthentication(firebaseToken);
      SecurityContextHolder.getContext().setAuthentication(auth);

      log.info("Request user >>> {}", auth.getPrincipal().getEmail());
    } catch (FirebaseAuthException e) {
      this.authorException(request, response,
          new FirebaseAuthException(
              e.getErrorCode(),
              e.getMessage(),
              e.getCause(),
              e.getHttpResponse(),
              e.getAuthErrorCode()));
      return;
    } catch (Exception e) {
      this.authorException(request, response, new BadCredentialsException(e.getMessage()));
      return;
    }

    filterChain.doFilter(request, response);
  }

  private void authorException(
      HttpServletRequest request,
      HttpServletResponse response,
      Exception ex) {
    log.error("Firebase exception >>> {}", ex.getMessage());
    SecurityContextHolder.clearContext();
    ContextAccessor.getBean(HandlerExceptionResolver.class, "handlerExceptionResolver")
        .resolveException(request, response, null, ex);
  }

}
