package trizu.havefun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import trizu.havefun.domain.User;
import trizu.havefun.service.UserDetailsServiceImpl;

@Controller
public class UserController {

    private UserDetailsServiceImpl userDetailsService;
    private PasswordEncoder encoder;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService, PasswordEncoder encoder){
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @GetMapping("/signup")
    @ModelAttribute
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(@ModelAttribute("user") User user, BindingResult bindingResult){
        if(userDetailsService.existsUserByUsername(user.getUsername())){
            bindingResult.addError(new FieldError("user", "username", "Please chose different username"));
        }
        if(bindingResult.hasErrors()){
            return "signup";
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userDetailsService.save(user);
        return "home";
    }
}
