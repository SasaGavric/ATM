package korisnik;

public class Administrator extends Osoba {

	private final String prezime;
	private String brTelefona;

	public Administrator() {
		super("Sasa", "SberBank");

		this.prezime = "Gavric";
		this.brTelefona = "066/041-628";
	}

	public String getPrezime() {
		return prezime;
	}

	public String getBrTelefona() {
		return brTelefona;
	}

	@Override
	public boolean autentifikacija(String ime, String sifra) {

		if (getIme().equals(ime) && getSifra().equals(sifra)) {
			System.out.println("\nUlaz odobren");
			return true;
		} else {
			System.out.println("Pogresno ime ili sifra!");
			return false;
		}

	}

	@Override
	public void meni() {

		System.out.println("\nAdministrator:");
		System.out.println("==================================================");
		System.out.println("1.Stanje bankomata\n2.Pregled racuna svih klijenata"
				+ "\n3.Dodaj novog klijenta\n4.Ukloni klijenta\n5.Promijenite stanje novcanica\n6.izlaz");
		System.out.println("==================================================\n");
		System.out.print("Unos:");

	}

}
