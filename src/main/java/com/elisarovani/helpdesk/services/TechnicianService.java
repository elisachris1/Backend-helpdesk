package com.elisarovani.helpdesk.services;

import com.elisarovani.helpdesk.domain.Technician;
import com.elisarovani.helpdesk.domain.dtos.TechnicianDTO;
import com.elisarovani.helpdesk.repositories.TechnicianRepository;
import com.elisarovani.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    TechnicianRepository repository;

    public Technician findById(Integer id){
        Optional<Technician> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id));
    }

    public List<Technician> findAll() {
        return repository.findAll();
    }

    public Technician create(TechnicianDTO objDTO) {
        objDTO.setId(null);
    Technician newObj = new Technician(objDTO);
    return repository.save(newObj);
    }
}
