package kolokvijumISA.app.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Zadatak {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable = false)
	String oblast;
	@Column(nullable = false)
	String tekst;
	@Column(nullable = false)
	LocalDateTime pocetak;
	@Column(nullable = false)
	LocalDateTime kraj;
	
	@OneToMany(mappedBy = "zadatak", fetch = FetchType.LAZY)
	List<Rezultat> rezultati;
	
	public Zadatak(Long id, String oblast, String tekst, LocalDateTime pocetak, LocalDateTime kraj) {
		super();
		this.id = id;
		this.oblast = oblast;
		this.tekst = tekst;
		this.pocetak = pocetak;
		this.kraj = kraj;
	}
	
	public Zadatak() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOblast() {
		return oblast;
	}

	public void setOblast(String oblast) {
		this.oblast = oblast;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public LocalDateTime getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDateTime getKraj() {
		return kraj;
	}

	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}

	public List<Rezultat> getRezultati() {
		return rezultati;
	}

	public void setRezultati(List<Rezultat> rezultati) {
		this.rezultati = rezultati;
	}
	
	
	
	
}
