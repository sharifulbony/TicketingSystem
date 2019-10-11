package com.sharifulbony.ticketing.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity,Integer> {

    List<TicketEntity> findByStatus(short status);
    TicketEntity findByEmail(String email);


}
