package korisnik;

public class Klijent extends Osoba{

	private double racun;

	public Klijent(String ime) {
		super(ime);
	}

	public Klijent(String ime, double racun, String sifra) {
		super(ime, sifra);
		this.racun = racun;
	}

	public double getRacun() {
		return racun;
	}

	public void setRacun(double kolicina) {
		if (this.racun - kolicina >= 0) {
			System.out.println("Transakcija uspijesna");
			this.racun -= kolicina;
		} else {
			System.out.println("Nemate dovoljno novca na Vasem racunu!!!");
		}

	}

	@Override
	public boolean autentifikacija(String ime, String sifra) {

		if (this.getIme().equals(ime) && this.getSifra().equals(sifra)) {
			System.out.println("Ulaz odobren");
			return true;
		} else {
			System.out.println("Pogresno ime ili sifra!");
			return false;
		}

	}

	@Override
	public void meni() {
		System.out.println("\nKlijent:");
		System.out.println("==================================================");
		System.out.println("1.Pregled racuna\n2.Podigni novac\n3.Izlaz");
		System.out.println("==================================================\n");
		System.out.print("Unos:");

	}

	@Override
	public String toString() {
		return getIme() + "\t" + racun + "€";
	}

}
