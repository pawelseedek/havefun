package trizu.havefun.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import trizu.havefun.domain.User;
import trizu.havefun.service.UserService;
import trizu.havefun.service.impl.UserServiceImpl;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService, PasswordEncoder encoder){
        this.userService = userService;
    }

    @GetMapping("/signup")
    @ModelAttribute
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupPost(@ModelAttribute("user") User user, BindingResult bindingResult){
        if(userService.existsUserByUsername(user.getUsername())){
            bindingResult.addError(new FieldError("user", "username", "Please chose different username"));
        }
        if(bindingResult.hasErrors()){
            return "signup";
        }

        try{
            userService.save(user);
        }catch (DataIntegrityViolationException ex){
            System.out.println(ex.getMessage());
            bindingResult.addError(new FieldError("user", "username", "Something went wrong, please try again."));
            return "signup";
        }
        return "home";
    }
}
