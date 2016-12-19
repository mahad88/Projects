
import java.io.*;
import java.util.Scanner;

public class readFile {
	private BinaryTree bt;
	private spell_check sc;
	int count = 0;
	private Scanner in;
	int lookups = 0;

	public readFile() {
		this.in = new Scanner(System.in);
		this.sc = new spell_check();
	}

	/*
	 * This method call the methods readFile(),delete(),insert() menu() and the
	 * searchWord
	 */

	public void allMethods() {
		readFileToBinary();
		bt.delete("busybody");
		bt.insert("busybody");
		menu();
		searchWord();

	}

	/*
	 * This method in an infinite do while loop and allows the user to search
	 * for a word until he input 'q' which stop the program. upon quitting the
	 * the binary tree statics is printed out.
	 */
	public void searchWord() {
		String input = null;
		do {
			System.out.print("Search for a word-> ");
			input = in.nextLine().toLowerCase();
			search_filter(input);

		} while (!input.equals("q"));
		System.out.println("you have quit");
		bt.treeStatics();// prints the binary tree statistic
		in.close();
	}

	/*
	 * This method check if the user gives a right input and he/she finds  the
	 * word he/she is looking for or similar word are found.
	 */
	public void search_filter(String input) {

		if (input.contains(" ")) {
			System.out.println("The words have no spaces.Enter a single word.");
			return;

		}

		else if ((input.matches("-?\\d+(\\.\\d+)?"))) {
			System.out.println("Unknown input.Enter a word.");
			return;

		} else if (input.matches("")) {
			// System.out.println(".....");
			return;
		}

		else if (input.equals("h")) {
			menu();
		} else {
			String result = bt.search(input);

			if (result != null && result.equals(input)) {
				System.out.println("" + result
						+ " is found. Please enter another word.");
			} else if (!input.equals("q")) {

				sc.similar_words(input, bt);
			}

		}

	}

    /*This method read the dictionary.txt file and it insert the word to the binarytree 
    which is created also here.*/

	public void readFileToBinary() {

		try {
			Scanner sc = new Scanner(new File("dictionary.txt"));

			while (sc.hasNext()) {
				String word = sc.next();

				if (bt == null) {
					bt = new BinaryTree(word);
					count++;
				} else {
					bt.insert(word);
					count++;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
		    System.out.println("file not found");

		} finally {
			//System.out.println("words put in the binaytree are  " + count);

		}
	}

	public void menu() {
		System.out.println("THE MENU");
		System.out.println("---------------------:");
		System.out.print("(q)to Quit, (h) to print the menu");
		System.out.println("");

	}

	public boolean isLetter(String input) {

		return Character.isLetter(input.charAt(0));
	}

}
