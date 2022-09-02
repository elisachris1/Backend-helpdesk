package com.elisarovani.helpdesk.resources;


import com.elisarovani.helpdesk.domain.Client;
import com.elisarovani.helpdesk.domain.dtos.ClientDTO;
import com.elisarovani.helpdesk.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/clients")
public class ClientResources {

    @Autowired
    private ClientService service;
    @GetMapping(value = "/{id}")
   public ResponseEntity<ClientDTO> findById(@PathVariable Integer id){
        Client obj = this.service.findById(id);
        return ResponseEntity.ok().body(new ClientDTO(obj));
   }
   @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
       List<Client> list = service.findAll();
       List<ClientDTO> listDTO = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
       return ResponseEntity.ok().body(listDTO);
   }

   @PostMapping
   public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO objDTO){
    Client newObj = service.create(objDTO);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newObj.getId()).toUri();
    return ResponseEntity.created(null).build();
   }

   @PutMapping(value="/{id}")
   public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO objDTO){
    Client obj = service.update(id, objDTO);
    return ResponseEntity.ok().body(new ClientDTO(obj));
   }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
