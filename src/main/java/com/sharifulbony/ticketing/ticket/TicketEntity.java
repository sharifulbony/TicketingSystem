package com.sharifulbony.ticketing.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Calendar;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("email")
    private String email;
    @JsonProperty("description")
    private String description;
    @JsonProperty("priority")
    private short priority;
    private long timestamp= Calendar.getInstance().getTimeInMillis();
    private short status=0;

}
