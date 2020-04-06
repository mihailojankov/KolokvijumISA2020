package kolokvijumISA.app.controller;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kolokvijumISA.app.DTO.RoditeljDTO;
import kolokvijumISA.app.model.Roditelj;
import kolokvijumISA.app.service.RoditeljService;


@Controller
@CrossOrigin
@RequestMapping(path = "/roditelj")
public class RoditeljController {
	@Autowired
	RoditeljService service;
	ArrayList<RoditeljDTO> lista;
	
	//Dobavi sve
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<RoditeljDTO>> dobaviSve(){
		ModelMapper mm = new ModelMapper();
		
		lista = new ArrayList<RoditeljDTO>();
		for(Roditelj x:service.dobaviSve()) {
			lista.add(mm.map(x, RoditeljDTO.class));
		}
		
		return new ResponseEntity<ArrayList<RoditeljDTO>>(lista, HttpStatus.OK);
	}
	
	
	//Dobavi po ID
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RoditeljDTO> dobaviPoId(@PathVariable("id") Long id){
		ModelMapper mm = new ModelMapper();
		Roditelj existing = service.dobaviPoId(id);
		
		if(existing == null) {
			return new ResponseEntity<RoditeljDTO>(HttpStatus.NOT_FOUND);
		}
		RoditeljDTO obj = mm.map(existing, RoditeljDTO.class);
		return new ResponseEntity<RoditeljDTO>(obj, HttpStatus.OK);
		
	}
	
	//Dodavanje novog
	@RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Roditelj> dodajNovi(@RequestBody Roditelj obj) {
        if (service.dobaviPoId(obj.getId()) != null) {
            return new ResponseEntity<Roditelj>(HttpStatus.CONFLICT);
        }
        service.save(obj);
        return new ResponseEntity<Roditelj>(HttpStatus.OK);
    }
	
	//Izmena
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public ResponseEntity<Roditelj> izmeni(@RequestBody Roditelj obj) {
        Roditelj existing = service.dobaviPoId(obj.getId());
        
        if (existing == null) {
            return new ResponseEntity<Roditelj>(HttpStatus.NOT_FOUND);
        }
        //Menja se sta je potrebno
        existing.setIme(obj.getIme());
        existing.setPrezime(obj.getPrezime());
        
        service.save(existing);
        return new ResponseEntity<Roditelj>(HttpStatus.OK);
    }

    //Brisanje
    //Brisanje se vrsi preko ucenika jer je roditelj vezan za ucenika
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> brisanje(@PathVariable("id") Long id) {
    	
        if (service.dobaviPoId(id) == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        
        service.delete(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
