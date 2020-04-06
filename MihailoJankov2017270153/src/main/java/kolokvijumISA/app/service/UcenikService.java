package kolokvijumISA.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kolokvijumISA.app.model.Ucenik;
import kolokvijumISA.app.model.Zadatak;
import kolokvijumISA.app.repository.UcenikRepository;



@Service
public class UcenikService {
	
	@Autowired
	UcenikRepository repository;
	
	public Iterable<Ucenik> dobaviSve(){
		return repository.findAll();
	}
	
	public Ucenik dobaviPoId(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void save(Ucenik obj) {
		repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Iterable<Zadatak> pronadjiZadatke(Long id) {
		return repository.pronadjiZadatke(id);
	}
	
}
