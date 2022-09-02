package com.elisarovani.helpdesk.services;

import com.elisarovani.helpdesk.domain.Person;
import com.elisarovani.helpdesk.domain.Technician;
import com.elisarovani.helpdesk.domain.dtos.TechnicianDTO;
import com.elisarovani.helpdesk.repositories.PersonRepository;
import com.elisarovani.helpdesk.repositories.TechnicianRepository;
import com.elisarovani.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.elisarovani.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    TechnicianRepository repository;

    @Autowired
    PersonRepository personRepository;

    public Technician findById(Integer id){
        Optional<Technician> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id));
    }

    public List<Technician> findAll() {
        return repository.findAll();
    }

    public Technician create(TechnicianDTO objDTO) {
        objDTO.setId(null);
        validateByEmail(objDTO);
    Technician newObj = new Technician(objDTO);
    return repository.save(newObj);
    }

    public Technician update(Integer id, TechnicianDTO objDTO) {
        objDTO.setId(id);
        Technician oldObj = findById(id);
        validateByEmail(objDTO);
        oldObj = new Technician(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Technician obj = findById(id);
        if(obj.getCalls().size() > 0){
            throw new DataIntegrityViolationException("Technician has calls and cannot be deleted!");
        }
            repository.deleteById(id);

    }

    private void validateByEmail(TechnicianDTO objDTO) {
     Optional<Person> obj = personRepository.findByEmail(objDTO.getEmail());
     if(obj.isPresent() && obj.get().getId()!= objDTO.getId()){
        throw new DataIntegrityViolationException("Email already registered in the system!");
        }
    obj = personRepository.findByEmail(objDTO.getEmail());


    }



}
