
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Soduko {

	private int[][] brettArr;
	Brett br = null;

	Soduko() {

	}

	/* leser fra fil aa operater brett objekt */
	public void lessFile(String file) throws FileNotFoundException, Excess {
		Scanner scan = new Scanner(new File(file));

		int rad = 0;
		int kolon = 0;
		int siz = 0;
		int teller = 0;
		rad = scan.nextInt();
		kolon = scan.nextInt();
		scan.nextLine();
		siz = rad * kolon;
		if (rad * kolon >= 64)
		throw new Excess(); // throw exception hvis bretter er 64*64

		brettArr = new int[siz][siz];
		Brett br;
		while (scan.hasNextLine()) {

			char[] temp = scan.nextLine().toCharArray();

			for (int i = 0; i < siz; i++) {
				if (temp[i] != '.') {
					if (Character.isDigit(temp[i])) {
						brettArr[teller][i] = Integer.parseInt("" + temp[i]);
						

					}

				}
			}

			teller++;

		}

		br = new Brett(brettArr, rad, kolon);

		skrivUt(); // skriv ut ulaast bretter
		br.opprettDatastruktur();  // operate ruter datastruktur
		// br.skrivUtRute();
		br.muligLaasning();    // skriv ut mulig laasning i blanke ruter
		scan.close();
	}
	// skriv ut ulaast bretter
	public void skrivUt() {
		System.out.println("Ulaast brett");
		int count = 0;
		for (int i = 0; i < brettArr.length; i++) {
			for (int j = 0; j < brettArr.length; j++) {
				if (brettArr[i][j] != 0) {
					if (j != 0 && j % 2 == 0 && j != 4)
						System.out.print(brettArr[i][j] + "|");
					else
						System.out.print(brettArr[i][j]);
				} else {
					if (j != 0 && j % 2 == 0 && j != 4)
						System.out.print(' ' + "|");
					else
						System.out.print(' ');
				}
			}

			System.out.println("");
			count++;
			if (count % 2 == 0 && count != brettArr.length)

				System.out.println("---+---");

		}
		System.out.println();
	}

}
