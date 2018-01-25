package atm;

import java.util.Scanner;

public class Main {

	private static Scanner unos = new Scanner(System.in);
	private static ATM bankomat = new ATM();

	public static void main(String[] args) {
		bankomat.ucitajKlijente();
		boolean izvrsi = true;
		while (izvrsi) {
			switch (prijava()) {
			case 0:
				unos.nextLine();
				continue;
			case 1:
				bankomat.administrator();
				break;

			case 2:
				bankomat.klijent();
				break;

			case 3:
				System.out.println("Hvala na povjerenju");
				izvrsi = false;
				break;
			}
		}

	}

	public static int prijava() {

		System.out.println("Bankomat-Sberbank a.d. Banja Luka");
		System.out.println("\nUlogujte se kao: \n1.Administrator\n2.Korisnik\n\nZa Izlaz pritisnite 3");

		while (true) {

			try {

				System.out.print("\n\nUnos: ");
				int izbor = unos.nextInt();

				if (izbor == 1) {
					return 1;

				} else if (izbor == 2) {
					return 2;

				} else if (izbor == 3) {
					return 3;
				}
			} catch (Exception e) {
				System.out.println("Pogresan unos!\nIzaberite 1,2 ili 3\n");
				return 0;
			}

		}

	}

}
