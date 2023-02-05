package trizu.havefun.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import trizu.havefun.domain.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Iterable<Ticket> findTicketByOwnerId(long ownerId);
}
