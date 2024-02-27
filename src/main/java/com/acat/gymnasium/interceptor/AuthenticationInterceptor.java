package com.acat.gymnasium.interceptor;

import com.acat.gymnasium.aop.Auth;
import com.acat.gymnasium.dao.GymnasiumTrainerDao;
import com.acat.gymnasium.domain.GymnasiumTrainer;
import com.acat.gymnasium.util.JwtUtils;
import com.auth0.jwt.exceptions.JWTDecodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private GymnasiumTrainerDao gymnasiumTrainerDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        String token = request.getHeader("token");
//        String token = request.getSession().getAttribute("token") != null ? request.getSession().getAttribute("token").toString() : null;
        if (method.isAnnotationPresent(Auth.class)) {
            Auth auth = method.getAnnotation(Auth.class);
            if (!auth.required()) {
                return true;
            }else {
                if (StringUtils.isBlank(token)) {
                    throw new RuntimeException("您尚未登录，请先登录！！！");
                }

                try {
                    String userId = JwtUtils.getUserId(token);
                    GymnasiumTrainer gymnasiumTrainer = gymnasiumTrainerDao.selectById(Integer.parseInt(userId));
                    if (gymnasiumTrainer == null) {
                        throw new RuntimeException("该用户不存在，请重新登录");
                    }
                    JwtUtils.checkSign(token);
                }catch (JWTDecodeException e) {
                    log.error("AuthenticationInterceptor.preHandle has error",e.getMessage());
                    throw new RuntimeException("token认证名称错误，请重新登录");
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {

    }
}
