package com.helloevent.backend.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Reservations")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dateReservation")
    private String dateReservation;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "EventId")
    private Event event;


}
