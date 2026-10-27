package com.telusko.TicketBookingWEBAPP.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.telusko.TicketBookingWEBAPP.model.Passenger;
import com.telusko.TicketBookingWEBAPP.model.Ticket;
import com.telusko.TicketBookingWEBAPP.service.ITicketService;

@Controller
public class TicketController {

    @Autowired
    private ITicketService service;

    @GetMapping("/passenger-form")
    public String passengerForm(Model model) {

        model.addAttribute("passenger", new Passenger());

        return "index";
    }

    @PostMapping("/book-ticket")
    public String bookTicket(
            @ModelAttribute Passenger passenger,
            Model model) {

        Integer ticketNumber = service.registerPassenger(passenger);

        model.addAttribute("passenger", new Passenger());
        model.addAttribute("ticketNumber", ticketNumber);

        return "index";
    }

    @GetMapping("/ticket-form")
    public String ticketForm() {
        return "ticket-form";
    }

    @GetMapping("/get-ticket")
    public String getTicket(
            @RequestParam("ticketNumber") Integer ticketNumber,
            Model model) {

        Ticket ticket = service.getFullTicket(ticketNumber);

        model.addAttribute("ticket", ticket);

        return "ticket-info";
    }
}