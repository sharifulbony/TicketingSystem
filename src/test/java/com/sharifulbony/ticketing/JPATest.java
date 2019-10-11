package com.sharifulbony.ticketing;
import com.sharifulbony.ticketing.ticket.TicketEntity;
import com.sharifulbony.ticketing.ticket.TicketRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JPATest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TicketRepository ticketRepository;
    @Test
    public void whenFindByEmail_thenReturnTicket() {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTitle("sampleTestTicket");
        ticketEntity.setEmail("a@b.c");
        ticketEntity.setDescription("sample description");
        ticketEntity.setPriority((short) 2);
        entityManager.persist(ticketEntity);
        entityManager.flush();
        TicketEntity found =  ticketRepository.findByEmail("a@b.c");
        assertThat(found.getEmail())
                .isEqualTo(ticketEntity.getEmail());
    }

    @Test
    public void checkIfUpdateTicket() {
        // given
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setTitle("sampleTestTicket");
        ticketEntity.setEmail("a@b.c");
        ticketEntity.setDescription("sample description");
        ticketEntity.setPriority((short) 2);
        entityManager.persist(ticketEntity);
        entityManager.flush();
        String changedTitle="changedTitle";
        ticketEntity.setTitle(changedTitle);
        entityManager.persistAndFlush(ticketEntity);
        assertThat(ticketEntity.getTitle())
                .isEqualTo(changedTitle);
    }
}
