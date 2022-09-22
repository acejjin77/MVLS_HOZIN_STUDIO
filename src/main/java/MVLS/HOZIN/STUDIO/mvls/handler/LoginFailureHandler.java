
package MVLS.HOZIN.STUDIO.mvls.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String admId = request.getParameter("admId");

        // TODO: 에러 메시지 View에 전달
        String errorMsg;
        if ((exception instanceof BadCredentialsException) || (exception instanceof UsernameNotFoundException)) {
            errorMsg = "아이디/비밀번호가 맞지 않습니다. 다시 로그인하여주십시오.";
        } else {
            errorMsg = "알 수 없는 이유로 로그인에 실패하였습니다 관리자에게 문의하세요.";
        }

        request.setAttribute("errorMsg", errorMsg);

        request.getRequestDispatcher("/login").forward(request, response);

    }
}
