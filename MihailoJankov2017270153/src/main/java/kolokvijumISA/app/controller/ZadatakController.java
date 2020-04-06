package kolokvijumISA.app.controller;

import java.time.LocalDateTime;
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
import kolokvijumISA.app.DTO.ZadatakDTO;
import kolokvijumISA.app.model.Roditelj;
import kolokvijumISA.app.model.Zadatak;
import kolokvijumISA.app.service.ZadatakService;

@Controller
@CrossOrigin
@RequestMapping(path="/zadatak")
public class ZadatakController {
	@Autowired
	ZadatakService service;
	ArrayList<ZadatakDTO> lista;
	
	//Dobavi sve
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ZadatakDTO>> dobaviSve(){
		ModelMapper mm = new ModelMapper();
		
		lista = new ArrayList<ZadatakDTO>();
		for(Zadatak x:service.dobaviSve()) {
			lista.add(mm.map(x, ZadatakDTO.class));
		}
		
		return new ResponseEntity<ArrayList<ZadatakDTO>>(lista, HttpStatus.OK);
	}
	
	
	//Dobavi po ID
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ZadatakDTO> dobaviPoId(@PathVariable("id") Long id){
		ModelMapper mm = new ModelMapper();
		Zadatak existing = service.dobaviPoId(id);
		
		if(existing == null) {
			return new ResponseEntity<ZadatakDTO>(HttpStatus.NOT_FOUND);
		}
		ZadatakDTO obj = mm.map(existing, ZadatakDTO.class);
		return new ResponseEntity<ZadatakDTO>(obj, HttpStatus.OK);
		
	}
	
	//Dodavanje novog
	@RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Zadatak> dodajNovi(@RequestBody Zadatak obj) {
        if (service.dobaviPoId(obj.getId()) != null) {
            return new ResponseEntity<Zadatak>(HttpStatus.CONFLICT);
        }
        service.save(obj);
        return new ResponseEntity<Zadatak>(HttpStatus.OK);
    }
	
	//Izmena
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public ResponseEntity<Zadatak> izmeni(@RequestBody Zadatak obj) {
        Zadatak existing = service.dobaviPoId(obj.getId());
        
        if (existing == null) {
            return new ResponseEntity<Zadatak>(HttpStatus.NOT_FOUND);
        }
        
        LocalDateTime now = LocalDateTime.now();
        
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss");

        //String formatDateTime = now.format(formatter);
        
        //Menja se sta je potrebno
        existing.setOblast(obj.getOblast());
        existing.setTekst(obj.getTekst());
        existing.setPocetak(now);
        existing.setKraj(obj.getKraj());
        
        service.save(existing);
        return new ResponseEntity<Zadatak>(HttpStatus.OK);
    }

    //Brisanje
    //Brisanje se vrsi jedino u slucaju da nema asocijacija u drugim tabelama
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> brisanje(@PathVariable("id") Long id) {
    	
        if (service.dobaviPoId(id) == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        }
        
        service.delete(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    
    //Pronadji roditelje
    @RequestMapping(path = "/pronadjiRoditelja/{id}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<RoditeljDTO>> pronadjiRoditelja(@PathVariable("id") Long id) {
    	ModelMapper mm = new ModelMapper();
		
		ArrayList<RoditeljDTO>listaR = new ArrayList<RoditeljDTO>();
		for(Roditelj x:service.pronadjiRoditelja(id)) {
			listaR.add(mm.map(x, RoditeljDTO.class));
		}
		
		return new ResponseEntity<ArrayList<RoditeljDTO>>(listaR, HttpStatus.OK);
    }
}
