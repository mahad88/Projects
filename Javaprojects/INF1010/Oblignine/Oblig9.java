

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;

public class Oblig9 {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Gir: antalltraader, input fil, output-fil");
			System.exit(1);
		}
		int antallTaader = Integer.parseInt(args[0]);
		String inputFile = args[1];
		String utFile = args[2];

		Monitor monitor = new Monitor(antallTaader, utFile);
		try {
			monitor.readFile(inputFile);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Fil ikke ble funnet");
			return;

		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out
					.println("Forst Linje data ikke lik med antall ord");
			return;

		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Fil har  feil data");
			return;

		}
		int traadNr = 0;
		// totalorder deles opp for hver traad
		int ord_per_Thread = monitor.hentArraylength() / antallTaader;
		// resten atte deling opp av order
		int rest_Ord = monitor.hentArraylength() % antallTaader;
		// beginelsen index for hver trader
		int start_Pos = (monitor.hentArraylength() / antallTaader) * traadNr;
		int slutt_Pos = 0; // slutt index for hver traad

		for (int i = 0; i < antallTaader; i++) {
			slutt_Pos = start_Pos + ord_per_Thread;
			if (rest_Ord > 0) {
				rest_Ord--;
				slutt_Pos++;

			}
			new Traad(start_Pos, slutt_Pos, monitor).start();
			start_Pos = slutt_Pos;
			traadNr++;

		}

	}

}
