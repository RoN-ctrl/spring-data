package com.learn.springmvc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date date;

    private Double price;

    public Event(String title, Date date, double price) {
        this.title = title;
        this.date = date;
        this.price = price;
    }

}
