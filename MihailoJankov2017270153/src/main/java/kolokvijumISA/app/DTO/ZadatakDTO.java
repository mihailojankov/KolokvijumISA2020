package kolokvijumISA.app.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ZadatakDTO {
	Long id;
	String oblast;
	String tekst;
	LocalDateTime pocetak;
	LocalDateTime kraj;

	
	
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
	
	
	
}
