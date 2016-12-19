

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Oblig3 {

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("input: java Oblig3 <needle> <haystack>");
			System.out.println("check READme for more information");
			System.exit(1);
		}
		SearchWords swc = new SearchWords();

		Scanner sc = null;
		Scanner sc1 = null;
		String needle = "";
		String haystack = "";
		try {
			sc = new Scanner(new File(args[0]));
			while (sc.hasNext()) {
				needle += sc.nextLine();
			}
			sc1 = new Scanner(new File(args[1]));
			while (sc1.hasNext()) {
				haystack += sc1.nextLine();
				haystack += "\n ";
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error no needle or Haystack file ");
			System.exit(0);
		} catch (NoSuchElementException e) {
			System.out.println("Error needle or Haystack file not found");
			System.exit(0);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBoundsException!");
			System.exit(0);
		} catch (NumberFormatException e) {
			System.out.println("Number Format Exception.....");
			System.exit(0);
		}
		sc.close();
		sc1.close();
		if (needle.isEmpty()) {
			System.out.println("needle  is empty");
			return;
		}
		if (haystack.isEmpty()) {
			System.out.println(" haystack is empty");
			return;
		}

		List<Integer> matches = swc.bmSearch(needle, haystack);
		System.out.println("The needle is	" + needle);
		if (matches.isEmpty())
			System.out.println("	No match found");
		for (Integer offset : matches) {
			System.out.println("----------------------------");
			System.out.println("	index:	" + offset);
			System.out.println("	match:	"
					+ haystack.substring(offset, offset + needle.length()));

		}
		System.out.println("---------------------------	");
	}

}
