package trizu.havefun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import trizu.havefun.domain.Ticket;
import trizu.havefun.repository.TicketRepository;

@Controller
@RequestMapping("/userPanel")
public class UserPanelController {

    private TicketRepository ticketRepository;

    @Autowired
    public UserPanelController(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @ModelAttribute(name = "tickets")
    public Iterable<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping
    public String userPanel(){
        return "userPanel";
    }
}
