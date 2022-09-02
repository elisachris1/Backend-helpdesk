package com.elisarovani.helpdesk.resources;


import com.elisarovani.helpdesk.domain.Technician;
import com.elisarovani.helpdesk.domain.dtos.TechnicianDTO;
import com.elisarovani.helpdesk.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/technicians")
public class TechnicianResources {

    @Autowired
    private TechnicianService service;
    @GetMapping(value = "/{id}")
   public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id){
        Technician obj = this.service.findById(id);
        return ResponseEntity.ok().body(new TechnicianDTO(obj));
   }
   @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
       List<Technician> list = service.findAll();
       List<TechnicianDTO> listDTO = list.stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
       return ResponseEntity.ok().body(listDTO);
   }

   @PostMapping
   public ResponseEntity<TechnicianDTO> create(@RequestBody TechnicianDTO objDTO){
    Technician newObj = service.create(objDTO);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newObj.getId()).toUri();
    return ResponseEntity.created(null).build();
   }
}
