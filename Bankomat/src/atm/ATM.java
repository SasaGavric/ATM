package atm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import korisnik.Administrator;
import korisnik.Klijent;

public class ATM {

	private String ime;
	private Novcanice atmNovac;
	private ArrayList<Klijent> listaKlijenata;
	private Administrator admin;
	private Path path = Paths.get("Klijenti");

	Scanner unos = new Scanner(System.in);

	public ATM() {
		super();
		this.ime = "Bankomat-Sberbank a.d. Banja Luka ";
		this.atmNovac = new Novcanice();
		this.admin = new Administrator();
		this.listaKlijenata = new ArrayList<>();

	}

	private void ispisiStanjeNaBankomatu() {
		System.out.println(ime + " " + atmNovac.toString());
	}

	void ucitajKlijente() {

		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try (BufferedReader br = Files.newBufferedReader(path)) {

			String podatak;
			while ((podatak = br.readLine()) != null) {
				String[] klijent;

				klijent = podatak.split("-");
				listaKlijenata.add(new Klijent(klijent[0], Double.parseDouble(klijent[1]), klijent[2]));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void prikazStanjaSvihKlijenata() {

		int redniBroj = 0;
		for (Klijent klijent : listaKlijenata) {
			System.out.println(redniBroj + "." + klijent.getIme() + "\t" + klijent.getRacun() + "€");
			redniBroj++;
		}

	}

	private void dodajNovogKlijenta() {
		unos.nextLine();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.toString(), true))) {

			System.out.print("Ime klijenta: ");
			String ime = unos.nextLine();
			bw.write(ime + "-");

			System.out.print("Stanje na racunu: ");
			double stanje = unos.nextDouble();
			bw.write(stanje + "-");
			unos.nextLine();

			System.out.print("Pocetna sifra klijenta: ");
			String sifra = unos.nextLine();
			bw.write(sifra);
			bw.newLine();

			listaKlijenata.add(new Klijent(ime, stanje, sifra));

		} catch (Exception e) {
			System.out.println("Pogresan unos");
			unos.nextLine();
		}

	}

	private void ukloniKlijenta() {
		unos.nextLine();
		prikazStanjaSvihKlijenata();

		try {
			System.out.print("\nUnesite redni broj klijenta koga zelite ukloniti: ");
			int izbor = unos.nextInt();
			System.out.println("Klijent " + listaKlijenata.get(izbor).getIme() + " izbrisan\n");
			listaKlijenata.remove(izbor);
			prikazStanjaSvihKlijenata();
			reUpis();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Klijent pod tim brojem ne postoji");
		} catch (Exception e) {
			System.out.println("Pogresan unos");
			unos.nextLine();
		}

	}

	private void reUpis() {
		try (BufferedWriter bw = Files.newBufferedWriter(path)) {

			for (Klijent klijent : listaKlijenata) {
				String podatak = klijent.getIme() + "-" + klijent.getRacun() + "-" + klijent.getSifra();
				bw.write(podatak + "\n");

			}
		} catch (Exception e) {
			System.out.println("Doslo je do greske!" + e.getMessage());
		}
	}

	private void promijeniStanjeNovcanica() {

		try {
			unos.nextLine();
			int izbor;
			ispisiStanjeNaBankomatu();
			System.out.println("\n\nPromijeni:\n1.Desetice\n2.Dvadesetice\n3.Pedesetice\n4.Stotice");
			System.out.print("\nUnos: ");
			izbor = unos.nextInt();

			System.out.print("Koliko zelite ubaciti/(-)izvaditi novca: ");
			int kolicina = unos.nextInt();
			switch (izbor) {
			case 1:
				atmNovac.setDesetice(kolicina);
				break;
			case 2:
				atmNovac.setDvadesetice(kolicina);
				break;
			case 3:
				atmNovac.setPedesetice(kolicina);
				break;
			case 4:
				atmNovac.setStotice(kolicina);
				break;
			default:
				System.out.println("Pogresan unos!");
				break;
			}

		} catch (Exception e) {
			System.out.println("Greska!!!");
		}

	}

	public void administrator() {
		String[] podaci = ulogujSe();

		while (true) {

			if (admin.autentifikacija(podaci[0], podaci[1])) {

				int izbor = 0;

				while (izbor != 6) {
					admin.meni();
					switch (izbor = unos.nextInt()) {
					case 1:
						ispisiStanjeNaBankomatu();
						break;
					case 2:
						prikazStanjaSvihKlijenata();
						break;
					case 3:
						dodajNovogKlijenta();
						break;
					case 4:
						ukloniKlijenta();
						break;
					case 5:
						promijeniStanjeNovcanica();
						break;
					}

				}

				System.out.println("Odjava uspijesna\n\n");
				unos.nextLine();
				break;

			} else {
				System.out.println("Pokusajte ponovo!\n");
				break;
			}
		}
		reUpis();
	}

	private int podigniNovac() {
		System.out.print("Koliko zelite da podignete novca: ");
		int kolicina = unos.nextInt();
		return kolicina;

	}

	public void klijent() {

		try {
			String[] podaci = ulogujSe();
			int klijentNadjen = -1;

			for (int i = 0; i < listaKlijenata.size(); i++) {
				if (listaKlijenata.get(i).getIme().equals(podaci[0])) {
					klijentNadjen = i;
				}
			}

			if (listaKlijenata.get(klijentNadjen).autentifikacija(podaci[0], podaci[1])) {

				while (true) {
					listaKlijenata.get(klijentNadjen).meni();
					int izbor = unos.nextInt();

					switch (izbor) {
					case 1:
						System.out.println(listaKlijenata.get(klijentNadjen).toString() + "\n\n");
						break;

					case 2:
						int kolicina = podigniNovac();
						if ((atmNovac.getUkupnoNovca() >= kolicina
								&& listaKlijenata.get(klijentNadjen).getRacun() >= kolicina) && (kolicina % 10 == 0)) {
							listaKlijenata.get(klijentNadjen).setRacun(kolicina);

							while (kolicina > 0) {
								if ((atmNovac.getStotice()) > 0 && (kolicina >= 100)) {
									atmNovac.setStotice(-1);
									kolicina -= 100;
								} else if ((atmNovac.getPedesetice()) > 0 && (kolicina >= 50)) {
									atmNovac.setPedesetice(-1);
									kolicina -= 50;
								} else if ((atmNovac.getDvadesetice()) > 0 && (kolicina >= 20)) {
									atmNovac.setDvadesetice(-1);
									kolicina -= 20;
								} else {
									atmNovac.setDesetice(-1);
									kolicina -= 10;
								}

							}

							reUpis();
						} else {
							System.out.println("Transakcija odbijena!");
						}
						break;
					}

					if (izbor == 3) {
						System.out.println("Odjava uspijesna\n\n");
						unos.nextLine();
						break;
					}
					unos.nextLine();
				}
			}
		} catch (Exception e) {
			System.out.println("Pogresno ime ili sifra!!!\n\n");
		}
	}

	private String[] ulogujSe() {

		String[] podatak = new String[2];

		System.out.print("Unesite korisnicko ime: ");
		podatak[0] = unos.nextLine();

		System.out.print("Unesite sifru: ");
		podatak[1] = unos.nextLine();

		return podatak;
	}

}
