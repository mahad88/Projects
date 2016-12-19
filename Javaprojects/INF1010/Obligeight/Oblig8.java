

import java.io.*;
import java.util.InputMismatchException;

public class Oblig8 {
	@SuppressWarnings("hiding")
	public static void main(String[] args) {
		Soduko sd = new Soduko();

		try {
			try {
				sd.lessFile("atte.txt");
				// sd.skrivUt();
			} catch (Excess e) {
				e.printStackTrace();
				e.message();
				return;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Fant ikke fil");
			return;
		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Ugyldig tegn i filen");
			return;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Tall utenfor lovlig intervall");
			return;
		}

	}

}
