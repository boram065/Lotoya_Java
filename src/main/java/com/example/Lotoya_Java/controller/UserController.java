package com.example.Lotoya_Java.controller;

//import com.example.Lotoya_Java.dto.UserContextHolder;
import com.example.Lotoya_Java.entity.User;
import com.example.Lotoya_Java.repository.UserRepository;
import com.example.Lotoya_Java.service.UserService;
import jakarta.persistence.EntityNotFoundException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController{
    private final UserService userService;
    private final UserRepository userRepository;
//    private final UserContextHolder userContextHolder;

    @GetMapping("/join")
    public String showJoinForm(Model model){
        model.addAttribute("user", new User());
        return "join";
    }

    @PostMapping("/join")
    public String processJoin(@ModelAttribute User user){
        userService.createUser(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        User user = userService.loginUser(email, password);

        if (user != null) {
            model.addAttribute("loggedInUser", user);
            return "redirect:/main";
        } else {
            model.addAttribute("error", "존재하지 않는 유저입니다.");
            return "redirect:/login";
        }
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

//    @PostMapping("/forecast")
//    public ResponseEntity<String> updateCoin(@RequestParam Long userId, @RequestParam Integer newCoinValue) {
//        userService.updateCoinValue(userId, newCoinValue);
//        return ResponseEntity.ok("Coin updated successfully");
//    }
//
//    @GetMapping("/yourMapping")
//    public String yourControllerMethod(HttpServletRequest request, Model model) {
//        User loggedInUser = userContextHolder.getLoggedInUser();
//
//        if (loggedInUser != null) {
//            // User 엔터티에 id 속성이 있어야 함
//            Long loggedInUserId = loggedInUser.getId();
//            model.addAttribute("loggedInUserId", loggedInUserId);
//            return "forecast";
//        } else {
//            return "redirect:/login";
//        }
//    }
}
