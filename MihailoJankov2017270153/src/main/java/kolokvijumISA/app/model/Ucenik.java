package kolokvijumISA.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Ucenik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(nullable = false)
	String ime;
	@Column(nullable = false)
	String prezime;
	
	@OneToOne(mappedBy = "ucenik", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	Roditelj roditelj;
	
	@OneToMany(mappedBy = "ucenik", fetch = FetchType.LAZY)
	List<Rezultat> rezultati;
	

	public Ucenik(Long id, String ime, String prezime) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
	}
	
	public Ucenik() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Roditelj getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(Roditelj roditelj) {
		this.roditelj = roditelj;
	}

	public List<Rezultat> getRezultati() {
		return rezultati;
	}

	public void setRezultati(List<Rezultat> rezultati) {
		this.rezultati = rezultati;
	}
	
	
	
	
}	
