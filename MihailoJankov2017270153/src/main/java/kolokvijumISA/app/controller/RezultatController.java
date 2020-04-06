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

import kolokvijumISA.app.DTO.RezultatDTO;
import kolokvijumISA.app.model.Rezultat;
import kolokvijumISA.app.service.RezultatService;


@Controller
@CrossOrigin
@RequestMapping(path = "/rezultat")
public class RezultatController {
	@Autowired
	RezultatService service;
	ArrayList<RezultatDTO> lista;
	
	//Dobavi sve
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<RezultatDTO>> dobaviSve(){
		ModelMapper mm = new ModelMapper();
		
		lista = new ArrayList<RezultatDTO>();
		for(Rezultat x:service.dobaviSve()) {
			lista.add(mm.map(x, RezultatDTO.class));
		}
		
		return new ResponseEntity<ArrayList<RezultatDTO>>(lista, HttpStatus.OK);
	}
	
	
	//Dobavi po ID
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RezultatDTO> dobaviPoId(@PathVariable("id") Long id){
		ModelMapper mm = new ModelMapper();
		Rezultat existing = service.dobaviPoId(id);
		
		if(existing == null) {
			return new ResponseEntity<RezultatDTO>(HttpStatus.NOT_FOUND);
		}
		RezultatDTO obj = mm.map(existing, RezultatDTO.class);
		return new ResponseEntity<RezultatDTO>(obj, HttpStatus.OK);
		
	}
	
	//Dodavanje novog
	@RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Rezultat> dodajNovi(@RequestBody Rezultat obj) {
        if (service.dobaviPoId(obj.getId()) != null) {
            return new ResponseEntity<Rezultat>(HttpStatus.CONFLICT);
        }
        service.save(obj);
        return new ResponseEntity<Rezultat>(HttpStatus.OK);
    }
	
	//Izmena
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public ResponseEntity<Rezultat> izmeni(@RequestBody Rezultat obj) {
        Rezultat existing = service.dobaviPoId(obj.getId());
        
        if (existing == null) {
            return new ResponseEntity<Rezultat>(HttpStatus.NOT_FOUND);
        }
        //Menja se sta je potrebno
        existing.setOcena(obj.getOcena());
        existing.setKomentar(obj.getKomentar());
        
        service.save(existing);
        return new ResponseEntity<Rezultat>(HttpStatus.OK);
    }

    //Brisanje
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> brisanje(@PathVariable("id") Long id) {
    	
        if (service.dobaviPoId(id) == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        
        service.delete(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
