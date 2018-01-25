package korisnik;

public abstract class Osoba {

	private String ime;
	private String sifra;

	public Osoba(String ime) {
		this.ime = ime;
	}

	public Osoba(String ime, String sifra) {
		super();
		this.ime = ime;
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public String getSifra() {
		return sifra;
	}

	public abstract boolean autentifikacija(String ime, String sifra);

	public abstract void meni();

}
