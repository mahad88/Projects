

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Oblig7 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		/**/
		Menustring m = new Menustring();
		m.menu();
		int option = 0;
		Scanner sc = new Scanner(System.in);

		while (option != 11) {

			option = sc.nextInt();
			switch (option) {

			case 1:
				try {
					m.readFile("siv.txt");
					System.out.println("Fil lesing var vellykket");
				} catch (FileNotFoundException e) {

					e.printStackTrace();

				}
				;
				break;

			case 2:
				try {
					m.skrivTilFile("output.txt");
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}
				;
				break;

			case 3:
				m.skrivUt_Beholder_Inhold();
				break;

			case 4:
				m.opprettLegemidler();
				break;

			case 5:
				m.opprettLeger();
				break;

			case 6:
				m.opprettResept();
				break;

			case 7:
				m.opprettPasient();
				break;
			case 8:
				m.hentLegemidler();
				break;

			case 9:
				m.menu();
				break;

			case 10:
				m.statistisk();
				break;

			case 11:
				option = 11;
				System.out.println("Du har avluttet program");
				break;

			default:
				System.out.println("feil ,Prøve igjen");
				break;
			}

		}
	}
}
