package atm;

public class Novcanice {

	private int desetice;
	private int dvadesetice;
	private int pedesetice;
	private int stotice;
	private int ukupnoNovca;

	public Novcanice() {

		this.desetice = 60;
		this.dvadesetice = 30;
		this.pedesetice = 20;
		this.stotice = 10;
		this.ukupnoNovca = (desetice * 10) + (dvadesetice * 20) + (pedesetice * 50) + (stotice * 100);
	}

	public int getDesetice() {
		return desetice;
	}

	public int getDvadesetice() {
		return dvadesetice;
	}

	public int getPedesetice() {
		return pedesetice;
	}

	public int getStotice() {
		return stotice;
	}

	public int getUkupnoNovca() {
		return ukupnoNovca;
	}

	public void setUkupnoNovca(int ukupnoNovca) {
		this.ukupnoNovca -= ukupnoNovca;
	}

	public void setDesetice(int kolicina) {
		int pocetno = this.desetice;
		if (desetice + kolicina <= 100) {
			this.desetice += kolicina;
			this.ukupnoNovca += 10 * (kolicina * 1);
		} else {
			System.out.println("Bankomat ne moze da obradi tu kolicinu novca!");
		}

		if (desetice < 0) {
			System.out.println("Bankomat ne raspolaze sa tom kolicinom novcanica\nIzdato je " + pocetno);
			this.desetice = 0;
		}
	}

	public void setDvadesetice(int kolicina) {
		int pocetno = this.dvadesetice;
		if (dvadesetice + kolicina <= 100) {
			this.dvadesetice += kolicina;
			this.ukupnoNovca += 20 * (kolicina * 1);
		} else {
			System.out.println("Bankomat ne moze da obradi tu kolicinu novca!");
		}

		if (dvadesetice < 0) {
			System.out.println("Bankomat ne raspolaze sa tom kolicinom novcanica\nIzdato je " + pocetno);
			this.dvadesetice = 0;
		}
	}

	public void setPedesetice(int kolicina) {
		int pocetno = this.pedesetice;
		if (pedesetice + kolicina <= 100) {
			this.pedesetice += kolicina;
			this.ukupnoNovca += 50 * (kolicina * 1);
		} else {
			System.out.println("Bankomat ne moze da obradi tu kolicinu novca!");
		}

		if (pedesetice < 0) {
			System.out.println("Bankomat ne raspolaze sa tom kolicinom novcanica\nIzdato je " + pocetno);
			this.pedesetice = 0;
		}
	}

	public void setStotice(int kolicina) {
		int pocetno = this.stotice;
		if (stotice + kolicina <= 100) {
			this.stotice += kolicina;
			this.ukupnoNovca += 100 * (kolicina * 1);
		} else {
			System.out.println("Bankomat ne moze da obradi tu kolicinu novca!");
		}

		if (stotice < 0) {
			System.out.println("Bankomat ne raspolaze sa tom kolicinom novcanica\nIzdato je " + pocetno);
			this.stotice = 0;
		}
	}

	@Override
	public String toString() {
		return "\n\nStanje:\nDesetice:" + desetice + "\nDvadesetice:" + dvadesetice + "\nPedesetice: " + pedesetice
				+ "\nStotice: " + stotice + "\n\nUkupno novca u bankomatu: " + ukupnoNovca + "€";
	}

}
