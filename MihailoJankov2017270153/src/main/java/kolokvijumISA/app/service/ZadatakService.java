package kolokvijumISA.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kolokvijumISA.app.model.Roditelj;
import kolokvijumISA.app.model.Zadatak;
import kolokvijumISA.app.repository.ZadatakRepository;

@Service
public class ZadatakService {
	@Autowired
	ZadatakRepository repository;
	
	public Iterable<Zadatak> dobaviSve(){
		return repository.findAll();
	}
	
	public Zadatak dobaviPoId(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void save(Zadatak obj) {
		repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Iterable<Roditelj> pronadjiRoditelja(Long id) {
		return repository.pronadjiRoditelje(id);
	}
}
