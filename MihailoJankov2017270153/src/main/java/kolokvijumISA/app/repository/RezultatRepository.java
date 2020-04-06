package kolokvijumISA.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kolokvijumISA.app.model.Rezultat;

@Repository
public interface RezultatRepository extends CrudRepository<Rezultat, Long>{

}
