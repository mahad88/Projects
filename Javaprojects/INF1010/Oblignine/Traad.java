

public class Traad extends Thread {

	private int start_Pos;
	private int slutt_Pos;
	private Monitor monitor;
	private String[] sortWords_Arr;  // antall data for hver traader
	private String[] forst_Sortet_Arr; //forste sortet array som flettes med andre
	

	public Traad(int start_Pos, int slutt_Pos, Monitor monitor) {

		this.start_Pos = start_Pos;
		this.slutt_Pos=slutt_Pos;
		this.monitor = monitor;

	}
/*Brukt insertion sort til aa sortere. De er ikke good algorithm  som quick og merge for store mengde
 * me er enkler og bruk og forstaalig.Etter hver sort kaller jeg fletting metoder  som er merge sort
 * algorithm so fletting sammen to array til en
 *  Deette var min invariant*/
	
	public void run() {
		String temp_Word;
		sortWords_Arr = new String[slutt_Pos - start_Pos];
		int teller = 0;
		for (int i = start_Pos; i < slutt_Pos; i++) {
			sortWords_Arr[teller++] = monitor.hentArrInhold()[i];
		}

		for (int i = 1; i < sortWords_Arr.length; i++) {
			temp_Word = sortWords_Arr[i];
			int j = 0;
			for (j = i; j > 0; j--)
				if (temp_Word.compareTo(sortWords_Arr[j - 1]) < 0)
					sortWords_Arr[j] = sortWords_Arr[j - 1];
				else
					break;
			sortWords_Arr[j] = temp_Word;
		}

		forst_Sortet_Arr = monitor.setInn(sortWords_Arr);
		if (forst_Sortet_Arr != null) {
			monitor.fletting(forst_Sortet_Arr, sortWords_Arr);
		}

	}

}
