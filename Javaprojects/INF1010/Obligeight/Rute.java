

public class Rute {

	private Rad rad;
	private Kolon kolon;
	private Box box;
	private int inhold;
	private int siz;

	public Rute(Rad rad, Kolon kolon, Box box, int inhold, int siz) {
		this.rad = rad;
		this.kolon = kolon;
		this.box = box;
		this.inhold = inhold;
		this.siz = siz;

	}

	public Rad hentRad() {
		return rad;
	}

	public Kolon hentKolon() {
		return kolon;
	}

	public Box hentBox() {
		return box;
	}

	public int hentInhold() {
		return inhold;
	}

	public int hentSiz() {
		return siz;
	}

	/* Finne mulig laasning i blanke rute og sette inn paa array som returneres */
	public int[] finnAlleMuligeTall() {
		System.out.println("");
		System.out.println("Rad " + rad.hentID() + " kolon " + kolon.hentID()
				+ " Box " + box.hentID());
		int muligArr[] = new int[hentSiz()];

		int n = 0;
		int count = 0;
		if (this.hentInhold() != 0)
			System.out.println("opptatt	");
		else {
			System.out.print("mulig losning er ");
			for (int i = 1; i <= hentSiz(); i++) {
				if (!kolon.inholder(i) && !box.inholder(i) && !rad.inholder(i)) {
					System.out.print(i + ", ");
					// inhold=i;
					muligArr[n++] = i;
					count++;
				}
			}
			System.out.println();

		}
		System.out.println("Antall losning " + count);
		return muligArr;
	}

}
