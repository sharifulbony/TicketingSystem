package com.sharifulbony.ticketing.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    private void validateData(TicketEntity ticketEntity){
        if(ticketEntity.getId()!=null&&ticketEntity.getId()<0){
            throw new RequestRejectedException("Invalid Id for Ticket");
        }
        if(ticketEntity.getTitle()!=null&&ticketEntity.getTitle().length()<1){
            throw new RequestRejectedException("Title can not be empty");
        }
        if(ticketEntity.getEmail()!=null&&ticketEntity.getEmail().length()<0){
            throw new RequestRejectedException("Email Can not empty");
        }
        if(ticketEntity.getDescription()!=null&&ticketEntity.getDescription().length()<0){
            throw new RequestRejectedException("Description can not be empty");
        }
//        if(ticketEntity.getPriority()){
//            throw new RequestRejectedException("Invalid Priority for Ticket");
//        }
    }

    public void saveData(TicketEntity ticketEntity){
        validateData(ticketEntity);
        ticketRepository.save(ticketEntity);
    }
}
