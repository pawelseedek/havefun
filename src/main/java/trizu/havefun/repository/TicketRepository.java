package trizu.havefun.repository;

import org.springframework.data.repository.CrudRepository;
import trizu.havefun.domain.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
