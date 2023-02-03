package trizu.havefun.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import trizu.havefun.domain.Ticket;
import trizu.havefun.domain.User;
import trizu.havefun.repository.TicketRepository;
import trizu.havefun.service.UserDetailsServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private TicketRepository ticketRepository;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public TicketController(TicketRepository ticketRepository, UserDetailsServiceImpl userDetailsService) {
        this.ticketRepository = ticketRepository;
        this.userDetailsService = userDetailsService;
    }

    @ModelAttribute(name = "ticket")
    public Ticket ticket(){
        return new Ticket();
    }

    @RequestMapping()
    public String ticketPage() {
        return "ticket";
    }

    @PostMapping()
    public String createTicket(@Valid Ticket ticket, Errors errors, Principal principal){
        if(errors.hasErrors()){
            return "ticket";
        }
        User user = userDetailsService.loadUserByUsername(principal.getName());
        ticket.setCreatedBy(user);
        ticket.setOwner(user);
        ticketRepository.save(ticket);
        return "redirect:/userPanel";
    }
}
