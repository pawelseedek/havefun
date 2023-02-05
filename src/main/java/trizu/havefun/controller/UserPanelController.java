package trizu.havefun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import trizu.havefun.domain.Ticket;
import trizu.havefun.repository.TicketRepository;
import trizu.havefun.repository.UserRepository;

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
