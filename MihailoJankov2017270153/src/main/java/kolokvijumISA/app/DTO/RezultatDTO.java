package kolokvijumISA.app.DTO;

import kolokvijumISA.app.model.Ucenik;
import kolokvijumISA.app.model.Zadatak;

public class RezultatDTO {
	Long id;
	int ocena;
	String komentar;
	UcenikDTO ucenik;
	ZadatakDTO zadatak;
	
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
	public UcenikDTO getUcenik() {
		return ucenik;
	}
	public void setUcenik(UcenikDTO ucenik) {
		this.ucenik = ucenik;
	}
	public ZadatakDTO getZadatak() {
		return zadatak;
	}
	public void setZadatak(ZadatakDTO zadatak) {
		this.zadatak = zadatak;
	}
	
	
	
	
}
