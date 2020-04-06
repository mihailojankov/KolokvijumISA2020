package kolokvijumISA.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rezultat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable = false)
	int ocena;
	@Column(nullable = false)
	String komentar;
	
	@ManyToOne
	@JoinColumn(name = "ucenik_id", nullable = false)
	Ucenik ucenik;
	
	@ManyToOne
	@JoinColumn(name = "zadatak_id", nullable = false)
	Zadatak zadatak;

	
	
	
	public Rezultat(Long id, int ocena, String komentar) {
		super();
		this.id = id;
		this.ocena = ocena;
		this.komentar = komentar;
	}
	
	public Rezultat() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Ucenik getUcenik() {
		return ucenik;
	}

	public void setUcenik(Ucenik ucenik) {
		this.ucenik = ucenik;
	}

	public Zadatak getZadatak() {
		return zadatak;
	}

	public void setZadatak(Zadatak zadatak) {
		this.zadatak = zadatak;
	}
	
	
	
}
