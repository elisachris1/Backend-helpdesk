package com.elisarovani.helpdesk.services;

import com.elisarovani.helpdesk.domain.Call;
import com.elisarovani.helpdesk.domain.Client;
import com.elisarovani.helpdesk.domain.Technician;
import com.elisarovani.helpdesk.domain.enums.Priority;
import com.elisarovani.helpdesk.domain.enums.Profile;
import com.elisarovani.helpdesk.domain.enums.Status;
import com.elisarovani.helpdesk.repositories.CallRepository;
import com.elisarovani.helpdesk.repositories.ClientRepository;
import com.elisarovani.helpdesk.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CallRepository callRepository;

    public void instanceDB(){
        Technician tec1 = new Technician(null, "Elisa Rovani", "elisa@email.com", "123" );
        tec1.addProfile(Profile.ADMIN);
        Technician tec2 = new Technician(null, "Thomas Lieven", "thomas@email.com", "123" );
        tec2.addProfile(Profile.ADMIN);
        Technician tec3 = new Technician(null, "Thor Odinson", "thor@email.com", "123" );
        tec2.addProfile(Profile.ADMIN);
        Technician tec4 = new Technician(null, "Daniel Sempere", "daniel@email.com", "123" );
        tec2.addProfile(Profile.ADMIN);

        Client cli1 = new Client(null, "Albert Einstein", "albert@email.com", "123" );
        Client cli2 = new Client(null, "Thomas Edson", "tedison@email.com", "123" );
        Client cli3 = new Client(null, "Steve Rogers", "steve@email.com", "123" );
        Client cli4 = new Client(null, "Tony Stark", "tony@email.com", "123" );


        Call c1 = new Call(null, Priority.MEDIUM, Status.ONGOING, "call01", "first call", tec2, cli3);
        Call c2 = new Call(null, Priority.HIGH, Status.ONGOING, "call02", "second call", tec3, cli1);
        Call c3 = new Call(null, Priority.LOW, Status.OPEN, "call03", "third call", tec4, cli2);
        Call c4 = new Call(null, Priority.MEDIUM, Status.CLOSED, "call04", "fourth call", tec1, cli4);
        Call c5 = new Call(null, Priority.MEDIUM, Status.ONGOING, "call05", "fifth call", tec3, cli4);

        technicianRepository.saveAll(Arrays.asList(tec1,tec2,tec3,tec4));
        clientRepository.saveAll(Arrays.asList(cli1, cli2,cli3,cli4));
        callRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5));
    }
}
