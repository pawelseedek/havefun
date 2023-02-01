package trizu.havefun.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import trizu.havefun.domain.Ticket;
import trizu.havefun.repository.TicketRepository;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
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
    public String createTicket(@Valid Ticket ticket, Errors errors){
        if(errors.hasErrors()){
            return "ticket";
        }

        ticketRepository.save(ticket);
        return "redirect:/userPanel";
    }
}
