package kolokvijumISA.app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kolokvijumISA.app.model.Roditelj;
import kolokvijumISA.app.repository.RoditeljRepository;

@Service
public class RoditeljService {
	
	@Autowired
	RoditeljRepository repository;
	
	public Iterable<Roditelj> dobaviSve(){
		return repository.findAll();
	}
	
	public Roditelj dobaviPoId(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void save(Roditelj obj) {
		repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
