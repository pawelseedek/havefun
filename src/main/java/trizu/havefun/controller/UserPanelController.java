package trizu.havefun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import trizu.havefun.domain.Ticket;
import trizu.havefun.domain.User;
import trizu.havefun.repository.TicketRepository;
import trizu.havefun.repository.UserRepository;
import trizu.havefun.service.UserDetailsServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/userPanel")
public class UserPanelController {

    private TicketRepository ticketRepository;
    private UserRepository userRepository;

    @Autowired
    public UserPanelController(TicketRepository ticketRepository, UserRepository userRepository){
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "tickets")
    public Iterable<Ticket> getAllTickets(Principal principal) {
        return ticketRepository.findTicketByOwnerId(userRepository.findByUsername(principal.getName()).getId());
    }

    @GetMapping
    public String userPanel(){
        return "userPanel";
    }
}
