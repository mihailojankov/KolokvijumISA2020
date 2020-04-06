package kolokvijumISA.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kolokvijumISA.app.model.Roditelj;
import kolokvijumISA.app.model.Zadatak;

@Repository
public interface ZadatakRepository extends CrudRepository<Zadatak, Long>{
	
	//Query ne vraca duplikate ni sa ni bez DISTINCT
	
	@Query("SELECT DISTINCT u.roditelj FROM Ucenik u "
			+ "INNER JOIN u.rezultati r "
			+ "WHERE r.zadatak.id = :zadatakid AND "
			+ "r.ocena < (SELECT AVG(ocena) FROM r WHERE r.zadatak.id = :zadatakid)")
	public Iterable<Roditelj> pronadjiRoditelje(@Param("zadatakid") Long id);
}
