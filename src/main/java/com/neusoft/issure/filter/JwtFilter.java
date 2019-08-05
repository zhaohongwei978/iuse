import com.auth0.jwt.interfaces.Claim;
import com.neusoft.issure.common.Responsive.Responsive;
import com.neusoft.issure.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        //请求 返回 对象
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        // Get authorization from Http request
         final String authHeader = request.getHeader("Authorization");
        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        response.setHeader("Access-Control-Allow-Origin", "*");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");
        //设置编码集
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        // If the Http request is OPTIONS then just return the status code 200
        // which is HttpServletResponse.SC_OK in this code
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        }
        // Except OPTIONS, other request should be checked by JWT
        else {
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                Responsive.of("400","token 不存在",res);
                return;
            }
            // Then get the JWT token from authorization
            final String token = authHeader.substring(7);
            Claims claims;
            try {
                // Use JWT parser to check if the signature is valid with the Key "secretkey"
                claims = JwtUtils.getClaims(token);

                // Add the claim to request header
                request.setAttribute("claims", claims);
            }catch (ExpiredJwtException e){
                logger.info("token超时");
                response.setContentType("application/json;charset=utf-8");
                response.addHeader("Access-Control-Allow-Origin", "*");
                Responsive.of("400","token 过期",res);
                return ;
            }
            filterChain.doFilter(req, res);
        }
    }
}