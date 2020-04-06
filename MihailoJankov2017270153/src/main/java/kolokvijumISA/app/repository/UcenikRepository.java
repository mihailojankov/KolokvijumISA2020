package kolokvijumISA.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kolokvijumISA.app.model.Ucenik;
import kolokvijumISA.app.model.Zadatak;

@Repository
public interface UcenikRepository extends CrudRepository<Ucenik, Long>{
	
	@Query("SELECT e FROM Zadatak e INNER JOIN e.rezultati t WHERE t.ucenik.id = :ucenikid AND t.ocena > (SELECT AVG(ocena) FROM t)")
	public Iterable<Zadatak> pronadjiZadatke(@Param("ucenikid") Long id);
}
