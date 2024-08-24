package tyagi.spring.testSpring.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tyagi.spring.testSpring.auth.user.User;
import tyagi.spring.testSpring.auth.user.UserRepo;
import tyagi.spring.testSpring.helper.ResponseDto;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepo userRepo;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7); // 7 karena mengambil jumlah karakter pada kata "Bearer "
        try {
            username = jwtService.extractUsername(jwtToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                Optional<User> userOpt = userRepo.findByUsername(username);

                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                userOpt.get().getUserAkses().stream().forEach(userAkses -> {
                    authorities.add(new SimpleGrantedAuthority(userAkses.getHakAkses().getKode().toString()));
                });

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userOpt.get(),
                        null, authorities);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);

                filterChain.doFilter(request, response);
            }
        } catch (ExpiredJwtException eje) {
            response.setHeader("error", eje.getMessage());
            ResponseDto<Boolean> error = new ResponseDto<>();
            List<String> errorMessage = new ArrayList<>();
            errorMessage.add(eje.getMessage());
            error.setErrorMessage(errorMessage);
            error.setCode(HttpStatus.UNAUTHORIZED.value());
            error.setStatus(false);
            error.setPayload(false);
            error.setMessage("Token Kedaluarsa");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        } catch (RuntimeException re) {
            response.setHeader("error", re.getMessage());
            ResponseDto<Boolean> error = new ResponseDto<>();
            List<String> errorMessage = new ArrayList<>();
            errorMessage.add(re.getMessage());
            error.setErrorMessage(errorMessage);
            error.setCode(HttpStatus.UNAUTHORIZED.value());
            error.setStatus(false);
            error.setPayload(false);
            error.setMessage("Login gagal");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        } catch (Exception e) {
            response.setHeader("error", e.getMessage());
            ResponseDto<Boolean> error = new ResponseDto<>();
            List<String> errorMessage = new ArrayList<>();
            errorMessage.add(e.getMessage());
            error.setErrorMessage(errorMessage);
            error.setStatus(false);
            error.setPayload(false);
            error.setMessage("Failed to save data");
            if (e.getMessage().contains("NoSuchElementException") || e.getMessage().contains("NotFoundException")) {
                error.setMessage(e.getLocalizedMessage());
                error.setCode(HttpStatus.NOT_FOUND.value());
                response.setStatus(HttpStatus.NOT_FOUND.value());
            } else if (e.getMessage().contains("RuntimeException")) {
                error.setMessage(e.getLocalizedMessage());
                error.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            } else {
                error.setMessage(e.getLocalizedMessage());
                error.setCode(HttpStatus.UNAUTHORIZED.value());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }

    }

}
