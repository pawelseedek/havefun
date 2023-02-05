package trizu.havefun.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import trizu.havefun.domain.Ticket;
import trizu.havefun.service.TicketService;
import trizu.havefun.service.impl.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private TicketService ticketService;
    private UserServiceImpl userDetailsService;

    @Autowired
    public TicketController(TicketService ticketService, UserServiceImpl userDetailsService) {
        this.ticketService = ticketService;
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
        ticketService.save(ticket);
        return "redirect:/userPanel";
    }
}
