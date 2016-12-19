

public class Box {
	private int siz;
	private static int nummer;
	Rute[] rut;
	int i = 0;
	private int UnikID;

	public Box(int siz) {
		this.siz = siz;
		rut = new Rute[siz];
		nummer++;
		UnikID = nummer;
	}

	public int hentStorelse() {
		return siz;

	}

	public int hentID() {
		return UnikID;
	}

	public void settInn(Rute r) {
		rut[i++] = r;
	}

	public boolean inholder(int a) {

		for (int j = 0; j < hentStorelse(); j++) {
			if (rut[j].hentInhold() == a)
				return true;

		}
		return false;

	}

}
