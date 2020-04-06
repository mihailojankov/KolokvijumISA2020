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

import kolokvijumISA.app.DTO.UcenikDTO;
import kolokvijumISA.app.DTO.ZadatakDTO;
import kolokvijumISA.app.model.Ucenik;
import kolokvijumISA.app.model.Zadatak;
import kolokvijumISA.app.service.UcenikService;

@Controller
@CrossOrigin
@RequestMapping(path = "/ucenik")
public class UcenikController {
	@Autowired
	UcenikService service;
	ArrayList<UcenikDTO> lista;
	
	//Dobavi sve
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<UcenikDTO>> dobaviSve(){
		ModelMapper mm = new ModelMapper();
		
		lista = new ArrayList<UcenikDTO>();
		for(Ucenik x:service.dobaviSve()) {
			lista.add(mm.map(x, UcenikDTO.class));
		}
		
		return new ResponseEntity<ArrayList<UcenikDTO>>(lista, HttpStatus.OK);
	}
	
	
	//Dobavi po ID
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UcenikDTO> dobaviPoId(@PathVariable("id") Long id){
		ModelMapper mm = new ModelMapper();
		Ucenik existing = service.dobaviPoId(id);
		
		if(existing == null) {
			return new ResponseEntity<UcenikDTO>(HttpStatus.NOT_FOUND);
		}
		UcenikDTO obj = mm.map(existing, UcenikDTO.class);
		return new ResponseEntity<UcenikDTO>(obj, HttpStatus.OK);
		
	}
	
	//Dodavanje novog
	@RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Ucenik> dodajNovi(@RequestBody Ucenik obj) {
        if (service.dobaviPoId(obj.getId()) != null) {
            return new ResponseEntity<Ucenik>(HttpStatus.CONFLICT);
        }
        service.save(obj);
        return new ResponseEntity<Ucenik>(HttpStatus.OK);
    }
	
	//Izmena
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public ResponseEntity<Ucenik> izmeni(@RequestBody Ucenik obj) {
        Ucenik existing = service.dobaviPoId(obj.getId());
        
        if (existing == null) {
            return new ResponseEntity<Ucenik>(HttpStatus.NOT_FOUND);
        }
        //Menja se sta je potrebno
        existing.setIme(obj.getIme());
        existing.setPrezime(obj.getPrezime());
        
        service.save(existing);
        return new ResponseEntity<Ucenik>(HttpStatus.OK);
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
    
  //Pronadji zadatke
    @RequestMapping(path = "/pronadjiZadatke/{id}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<ZadatakDTO>> pronadjiZadatke(@PathVariable("id") Long id) {
    	ModelMapper mm = new ModelMapper();
		
		ArrayList<ZadatakDTO> listaZ = new ArrayList<ZadatakDTO>();
		
		for(Zadatak x:service.pronadjiZadatke(id)) {
			listaZ.add(mm.map(x, ZadatakDTO.class));
		}
		
		return new ResponseEntity<ArrayList<ZadatakDTO>>(listaZ, HttpStatus.OK);
    }
    
    
}
