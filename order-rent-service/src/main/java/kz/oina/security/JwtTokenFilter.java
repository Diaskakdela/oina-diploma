package kz.oina.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtSettings jwtSettings;

    private JWTVerifier getJwtVerifier() {
        Algorithm algorithm = Algorithm.HMAC512(jwtSettings.getSecret());
        return JWT.require(algorithm).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("jwt filter");

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            var decodedJWT = getJwtVerifier().verify(token.substring(7));

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    decodedJWT.getSubject(), null, List.of(new SimpleGrantedAuthority("ROLE_" + decodedJWT.getClaim("role").asString()))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
