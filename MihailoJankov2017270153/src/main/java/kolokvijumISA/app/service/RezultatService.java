package kolokvijumISA.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kolokvijumISA.app.model.Rezultat;
import kolokvijumISA.app.repository.RezultatRepository;

@Service
public class RezultatService {
	
	@Autowired
	RezultatRepository repository;
	
	public Iterable<Rezultat> dobaviSve(){
		return repository.findAll();
	}
	
	public Rezultat dobaviPoId(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void save(Rezultat obj) {
		repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
