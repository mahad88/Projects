

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Monitor {
	String[] array_File; // holder data lest fra input fil
	private int antall_Traad; // antall traader fra input
	private String ventene_Array[]; // forste sortet array som flettes med andre
	private long start_Time;
	private long end_Time;
	private String utfile; // output fil
	private int antOrd; // forste linje i fil

	Monitor(int antall_Traad, String utfile) {
		this.antall_Traad = antall_Traad;
		this.utfile = utfile;

	}

	public int hentAntallTraad() {
		return antall_Traad;
	}

	public String hentUtFile() {
		return utfile;

	}

	/*
	 * Leser forst linje i fil , opprett array med storelsen av forste linje i
	 * fil. Leser resten av fil og put i array some ble opprettet. Har try catch
	 * i main for aa haantere feilmelding.
	 */
	public void readFile(String fil) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fil));

		antOrd = scan.nextInt();
		scan.nextLine();
		String line = "";
		array_File = new String[antOrd];
		int j = 0;// teller

		while (scan.hasNextLine()) {

			line = scan.nextLine();
			array_File[j++] = line;

		}

		// Hvis forste linje har mange ord enn antall i fil ord, program slutt
		if (ingen_null_Index(array_File) != antOrd) {
			System.out.println("Forstlinje er store enn antal order i fil");
			System.exit(1);

		}
		System.out.println("En fil med  " + j + " antall order ble lest");
	}

	public int hentArraylength() {
		return array_File.length;
	}

	public int hentAtntallOrd() {
		return antOrd;
	}

	/* return inholder av array som inholder data som ble lset fra fil */
	public String[] hentArrInhold() {
		String tempArr[] = new String[hentArraylength()];
		for (int i = 0; i < hentArraylength(); i++) {
			tempArr[i] = array_File[i];
		}
		return tempArr;
	}

	// foor aa sjekke det siste index i array ligge ikke null peker
	public int ingen_null_Index(String[] aa) {
		int x = 0;
		for (int i = 0; i < aa.length; i++) {
			if (aa[i] != null)
				x++;
		}
		return x;
	}

	/*
	 * Disse metoder er merge sort algorithm. to array flettet sammen en sortet
	 * rekkefolge til en stor array som har storelse av the de to
	 */
	public void fletting(String[] array1, String[] array2) {
		start_Time = System.nanoTime();
		int i = 0, j = 0, fl = 0;

		String[] flett_Arr = new String[array1.length + array2.length];

		while (i < array1.length && j < array2.length) {
			if (array1[i].compareTo(array2[j]) < 0) {
				flett_Arr[fl] = array1[i];
				i++;
			} else {
				flett_Arr[fl] = array2[j];
				j++;
			}
			fl++;
		}
		
		// kopiere til det store array
		System.arraycopy(array1, i, flett_Arr, fl, array1.length - i);
		System.arraycopy(array2, j, flett_Arr, fl, array2.length - j);

		// Bare skiver til file hvis hele arrary ble sortet
		if (flett_Arr.length == hentArraylength()) {
			try {
				skriv_Til_Fil(flett_Arr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// beregning av sortering aa fletting tid
			end_Time = System.nanoTime();
			double difference = (end_Time - start_Time) / 1e6;
			System.out.println("\n" + "Det tok " + difference
					+ " millisekunder til aa sortere og aa flette");

		} else {
			String[] flett_Arr_rest = setInn(flett_Arr);
			if (flett_Arr_rest != null) {
				fletting(flett_Arr_rest, flett_Arr); // forsett aa flette
														// recursively
			}
		}
	}

	/*
	 * Denne metoder hjelper til aa merge to array. Det forste array beholdes i
	 * sortet array. Nar de andre er sortet so det merges sammen og sorted array
	 * settes til null
	 */
	public synchronized String[] setInn(String[] Arr2) {
		String[]  Arr1=null;
		if (ventene_Array== null) {
			ventene_Array = Arr2;
			return Arr1;
		} else {
			Arr1= ventene_Array;
			ventene_Array = null;
			return Arr1;
		}
	}

	// returner true hvis gitt array er sortet
	public boolean isSorted(String[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i].compareTo(a[i + 1]) > 0) {
				return false;
			}
		}
		return true;
	}

	// Skrives til fil hvis git array det er sortet

	public void skriv_Til_Fil(String[] sortet_Ord) throws IOException {

		if (isSorted(sortet_Ord)) { // bare skriver til filer hvis de er sortet

			PrintWriter output = new PrintWriter(new FileWriter(hentUtFile()));

			for (int i = 0; i < sortet_Ord.length; i++) {
				output.println(sortet_Ord[i]);
			}
			System.out.println("\n" + sortet_Ord.length
					+ " sortet  order ble skrevet out til en fil ");
			output.close();

		} else
			System.out.println("array kunne ikke sorteres ");

	}
}
