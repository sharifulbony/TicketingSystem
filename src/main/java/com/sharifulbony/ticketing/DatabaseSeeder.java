package com.sharifulbony.ticketing;
import com.sharifulbony.ticketing.ticket.TicketEntity;
import com.sharifulbony.ticketing.ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class DatabaseSeeder implements CommandLineRunner {


    private TicketRepository ticketRepository;

    @Autowired
    public DatabaseSeeder(

            TicketRepository ticketRepository

    ) {

        this.ticketRepository= ticketRepository;

    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {


        int dataSize =5;

        int maxCategoryForProduct=3;



        for (int i=1;i<=5;i++){
            TicketEntity ticketEntity=new TicketEntity();
            ticketEntity.setTitle("sample"+i);
            ticketEntity.setEmail("a@b.c"+i);
            ticketEntity.setDescription("sample Description"+i);
            ticketEntity.setPriority((short) i);
            ticketRepository.save(ticketEntity);
        }



    }

    protected String getSaltString(int size) {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


}
