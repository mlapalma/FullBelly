package com.aieme.pleasedheart.restcontrollers;

import com.aieme.pleasedheart.models.Owner;
import com.aieme.pleasedheart.services.OwnerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> listAllOwners(){
        List<Owner> owners = ownerService.findAll();
        if (owners.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Owner>>(owners, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Owner> getOwner(@PathVariable("id") int id){
        Owner owner = ownerService.findById(id);
        if(owner==null)
            return new ResponseEntity("Unable to get a owner by that id",HttpStatus.NOT_FOUND);
        return new ResponseEntity<Owner>(owner, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner,UriComponentsBuilder ucBuilder){
        if(ownerService.exist(owner))
            return new ResponseEntity("Unable to create owner. A owner with that name already exists",
                    HttpStatus.CONFLICT);
        int ownerId = ownerService.insert(owner);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/owners/{id}").buildAndExpand(ownerId).toUri());
        return new ResponseEntity<Owner>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Owner> updateOwner(@PathVariable("id") int id, @RequestBody Owner owner) {
        Owner currentOwner = ownerService.findById(id);
        if(currentOwner==null)
            return new ResponseEntity("Owner does not exists",HttpStatus.NOT_FOUND);

        currentOwner.setName(owner.getName());
        currentOwner.setEmail(owner.getEmail());
        currentOwner.setPhone(owner.getPhone());

        ownerService.update(currentOwner);
        return new ResponseEntity<Owner>(currentOwner, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Owner> deleteOwner(@PathVariable("id") int id) {
        Owner currentOwner = ownerService.findById(id);
        if(currentOwner==null)
            return new ResponseEntity("Owner does not exists",HttpStatus.NOT_FOUND);

        ownerService.delete(id);
        return new ResponseEntity<Owner>(HttpStatus.NO_CONTENT);
    }

}
