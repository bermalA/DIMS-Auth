package jwt.dimsjwt.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwt.dimsjwt.DTO.LoginRequest;
import jwt.dimsjwt.DTO.ProfessorRegisterRequest;
import jwt.dimsjwt.DTO.StudentRegisterRequest;
import jwt.dimsjwt.model.Role;
import jwt.dimsjwt.model.User;
import jwt.dimsjwt.repository.UserRepository;
import jwt.dimsjwt.service.ProfessorService;
import jwt.dimsjwt.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final StudentService studentService;
    private final ProfessorService professorService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpServletResponse httpServletResponse;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        String userRole = (String) session.getAttribute("userRole");
        System.out.println("User role from session: " + userRole);
        if (userRole != null) {
            model.addAttribute("userRole", userRole);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, Model model, HttpSession session) {
        try {
            User user = userRepository.findByEmail(loginRequest.getEmail());
            if (user != null) {
                if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                    System.out.println("Login successful for user: " + user.getEmail());
                    Role userRole = user.getRole();
                    session.setAttribute("userRole", userRole.toString());
                    System.out.println("User role set in session: " + userRole.toString());
                    return index(model,httpSession);
                } else {
                    return "redirect:/login?error=EmailOrPasswordInvalid";
                }
            } else {
                return "redirect:/login?error=EmailOrPasswordInvalid";
            }
        } catch (Exception e) {
            System.out.println("Exception occurred during login: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/login?error=UserNotFound";
        }
    }


    @GetMapping("/register/student")
    public String showRegistrationForm() {
        return "studentregister";
    }

    @PostMapping("/register/student")
    public String registerStudent(@ModelAttribute StudentRegisterRequest studentRegister) {
        int result = studentService.saveStudentRegisterRequest(studentRegister);
        if (result > 0) {
            return "redirect:/";
        }
        return "redirect:/register/student?error";
    }

    @GetMapping("/register/professor")
    public String showProfessorRegistrationForm() {
        return "profregister";
    }

    @PostMapping("/register/professor")
    public String registerProfessor(@ModelAttribute ProfessorRegisterRequest professorRegister) {
        int result = professorService.saveProfessorRegisterRequest(professorRegister);
        if (result > 0) {
            return "redirect:/";
        }
        return "redirect:/register/professor?error";
    }
}
