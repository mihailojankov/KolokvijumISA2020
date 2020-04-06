package kolokvijumISA.app.DTO;


public class RoditeljDTO {
	Long id;
	String ime;
	String prezime;
	UcenikDTO ucenik;
	
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
	public UcenikDTO getUcenik() {
		return ucenik;
	}
	public void setUcenik(UcenikDTO ucenik) {
		this.ucenik = ucenik;
	}
	
	
}
