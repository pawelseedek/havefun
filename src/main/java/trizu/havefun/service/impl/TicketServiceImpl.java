package trizu.havefun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import trizu.havefun.domain.Ticket;
import trizu.havefun.domain.User;
import trizu.havefun.repository.TicketRepository;
import trizu.havefun.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private UserServiceImpl userDetailsService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserServiceImpl userDetailsService){
        this.ticketRepository = ticketRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void save(Ticket ticket) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userDetailsService.loadUserByUsername(currentUserName);
            ticket.setCreatedBy(user);
            ticket.setOwner(user);
            ticketRepository.save(ticket);
        }
    }
}
