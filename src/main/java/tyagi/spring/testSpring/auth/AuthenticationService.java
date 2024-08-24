package tyagi.spring.testSpring.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tyagi.spring.testSpring.auth.modelDto.AuthenticationRequest;
import tyagi.spring.testSpring.auth.modelDto.AuthenticationResponse;
import tyagi.spring.testSpring.auth.modelDto.RegisterRequest;
import tyagi.spring.testSpring.auth.user.Role;
import tyagi.spring.testSpring.auth.user.User;
import tyagi.spring.testSpring.auth.user.UserRepo;
import tyagi.spring.testSpring.auth.user.hakAkses.HakAkses;
import tyagi.spring.testSpring.auth.user.hakAkses.HakAksesRepo;
import tyagi.spring.testSpring.auth.user.userAkses.UserAkses;
import tyagi.spring.testSpring.config.JwtService;
import tyagi.spring.testSpring.exceptions.DuplicateResourceException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo repository;
    private final HakAksesRepo hakAksesRepo;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final ModelMapper modelMapper;

    private String generateAccessToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        List<String> listAkses = new ArrayList<>();
        claims.put("hakAkses", listAkses);

        return jwtService.generateToken(claims, user);
    }

    public AuthenticationResponse register(RegisterRequest request) {

        Optional<User> userOpt = repository.findByUsername(request.getEmail());
        if (userOpt.isPresent()) {
            throw new DuplicateResourceException("Username sudah digunakan", "Registrasi user gagal");
        }

        List<HakAkses> hakAksesList = hakAksesRepo.findAll();
        Set<UserAkses> userAksesList = new HashSet<>();

        User user = modelMapper.map(request, User.class);
        for (HakAkses hakAkses : hakAksesList) {
            UserAkses userAkses = new UserAkses();
            userAkses.setUser(user);
            userAkses.setHakAkses(hakAkses);
            userAksesList.add(userAkses);
        }
        user.setUsername(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserAkses(userAksesList);
        if (request.getEmail().equals("superadmin@superadmin")) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        user.setName(request.getName());
        user = repository.save(user);

        String accessToken = generateAccessToken(user);
        List<String> listAkses = new ArrayList<>();
        user.getUserAkses().stream().forEach(userAkses -> listAkses.add(userAkses.getHakAkses().getKode()));

        return new AuthenticationResponse(user.getName(), user.getUsername(), accessToken, listAkses);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        List<User> users = repository.findAll();
        if (users.isEmpty() && request.getEmail().equals("superadmin@superadmin") && request.getPassword().equals("123456")) {
            RegisterRequest registerRequest = new RegisterRequest(request.getEmail(), request.getPassword(), "superadmin");

            return this.register(registerRequest);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Optional<User> userOpt = repository.findByUsername(request.getEmail());

        String accessToken = generateAccessToken(userOpt.get());
        List<String> listAkses = new ArrayList<>();
        userOpt.get().getUserAkses().stream().forEach(userAkses -> listAkses.add(userAkses.getHakAkses().getKode()));

        return new AuthenticationResponse(userOpt.get().getName(), userOpt.get().getUsername(), accessToken, listAkses);
    }

}
