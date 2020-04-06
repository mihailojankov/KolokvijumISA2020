package kolokvijumISA.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kolokvijumISA.app.model.Roditelj;

@Repository
public interface RoditeljRepository extends CrudRepository<Roditelj, Long>{

}
