

public class Brett {

	int[][] brettArr = null;
	int rad = 0;
	int kolon = 0;
	Rad[] radArr = null;
	Kolon[] kolonArr;
	Box[] boxArr;
	Rute[][] ruteArr;
	
	

	public Brett(int[][] brettArr, int rad, int kolon) {
		this.brettArr = brettArr;
		this.rad = rad;
		this.kolon = kolon;
	}

	public int[][] hentBrettArr() {
		return brettArr;
	}

	public int hentRad() {
		return rad;
	}

	public int hentKolon() {
		return kolon;
	}

	public int hentStorelse() {
		return kolon * rad;
	}

	public Rute[][] hentRuteArr() {
		return ruteArr;
	}
/*oprett datastruktur for ruter*/
	public void opprettDatastruktur() {

		radArr = new Rad[hentStorelse()];
		kolonArr = new Kolon[hentStorelse()];
		boxArr = new Box[hentStorelse()];
		for (int i = 0; i < hentStorelse(); i++) {
			radArr[i] = new Rad(hentStorelse());
			kolonArr[i] = new Kolon(hentStorelse());
			boxArr[i] = new Box(hentStorelse());
		}

		ruteArr = new Rute[hentStorelse()][hentStorelse()];
		for (int i = 0; i < hentStorelse(); i++) {
			for (int j = 0; j < hentStorelse(); j++) {
				int box = hentRad() * (i / hentRad()) + (j / hentKolon());
				ruteArr[i][j] = new Rute(radArr[i], kolonArr[j], boxArr[box],
						brettArr[i][j], hentStorelse());
				radArr[i].settInn(ruteArr[i][j]);
				kolonArr[j].settInn(ruteArr[i][j]);
				boxArr[box].settInn(ruteArr[i][j]);
			}

		}
		//ruteArr[2][2].finnAlleMuligeTall();
	}

	public void skrivUtRute() {

		int count = 0;
		for (int i = 0; i < hentStorelse(); i++) {
			for (int j = 0; j < hentStorelse(); j++) {
				if (ruteArr[i][j].hentInhold() != 0) {
					if (j != 0 && j % 2 == 0 && j != 4)
						System.out.print(ruteArr[i][j].hentInhold() + "|");
					else
						System.out.print(ruteArr[i][j].hentInhold());
				} else {
					if (j != 0 && j % 2 == 0 && j != 4)
						System.out.print(' ' + "|");
					else
						System.out.print(' ');
				}
			}

			System.out.println("");
			count++;
			if (count % 2 == 0)
				System.out.println("---+---");

		}
	}
// Skriv ut mulig laasning fra blanke ruter
	public void muligLaasning() {
		System.out.println("Disse er mulig laasning");
		System.out.println("----------------------");
		for (int i = 0; i < hentStorelse(); i++) {
			for (int j = 0; j < hentStorelse(); j++) {

				ruteArr[i][j].finnAlleMuligeTall();

			}
			System.out.println();
		}
	}

}
