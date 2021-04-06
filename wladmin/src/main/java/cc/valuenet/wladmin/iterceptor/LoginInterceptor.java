// package cc.valuenet.wladmin.iterceptor;
//
// import cc.valuenet.wladmin.jwtT.JwtUtils;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import io.jsonwebtoken.Claims;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.commons.lang3.StringUtils;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.io.PrintWriter;
//
// /**
//  * @author Zcj
//  *
//  */
// @Slf4j
// public class LoginInterceptor implements HandlerInterceptor {
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         String accessToken = request.getHeader("token");
//         if (accessToken==null){
//             accessToken= request.getParameter("token");
//         }
//         if (StringUtils.isNotBlank(accessToken)){
//             Claims claims = JwtUtils.checkToken(accessToken);
//             if (claims==null){
//                 log.info("登陆过期·重新登陆");
//                 return false;
//             }
//             Integer id = (Integer)claims.get("id");
//             String name =(String) claims.get("name");
//             request.setAttribute("user_id",id);
//             request.setAttribute("name",name);
//             return true;
//         }
//         log.info("登陆过期·重新登陆");
//         return false;
//     }
//
//     public static void sendJsonMessage(HttpServletResponse response) throws IOException {
//         ObjectMapper objectMapper = new ObjectMapper();
//         response.setContentType("application/json;charset=utf_8");
//         PrintWriter writer = response.getWriter();
//         writer.print(objectMapper.writeValueAsString("代填"));
//         writer.close();
//         response.flushBuffer();
//     }
//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//     }
//
//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//     }
// }
