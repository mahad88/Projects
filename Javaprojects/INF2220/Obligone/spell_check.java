
import java.util.LinkedList;

public class spell_check {
	private LinkedList<String> relatedwords; // put in all words found
	private long start_time;
        private long end_time;
        private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	int lookups; // count all lookups

	public spell_check() {
		lookups = 0;
		

	}

     /*This method assumes the two adjacent strings are swapped  in either front, back or anywhere.
      it swaps every to adjacent word by calling the swap method..It searches  
      if any similar word in found in the  binarytree. if found, it adds to the linkedlist*/

	public void similarOne(String word, BinaryTree bt) {
		char[] word_array = word.toCharArray();
		char[] tmp;

		String[] words = new String[word_array.length - 1];

		for (int i = 0; i < word_array.length - 1; i++) {
			tmp = word_array.clone();
			words[i] = swap(i, i + 1, tmp);
			String smw = bt.search(words[i]);
			addToList(smw);
		}
	}

	private String swap(int a, int b, char[] word) {
		char tmp = word[a];
		word[a] = word[b];
		word[b] = tmp;

		return new String(word);
	}

    /*This method assumes one word is replaced in either front, back or anywhere
     it replaces with the  letter of the alphabet on every index of the given word.It searches  
     if any similar word in found in the  binarytree. if found, it adds to the linkedlist*/

	public void replacedOne(String word, BinaryTree bt) {
		char[] word_array = word.toCharArray();
		char[] tmp;
		for (int i = 0; i < word_array.length; i++) {
			tmp = word_array.clone();
			for (int j = 0; j < alphabet.length; j++) {
				tmp[i] = alphabet[j];
				String smw = bt.search(new String(tmp));
				addToList(smw);
			}
		}
	}

  /*This method assumes one word is missing in either front, back or anywhere
    it add one letter letter of the alphabet on every index of the given word.It searches  
   if any similar word in found in the  binarytree. if found, it adds to the linkedlist*/

	public void removedOne(String word, BinaryTree bt) {
		for (int i = 0; i < word.length() + 1; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				String tmp = word.substring(0, i) + alphabet[j]
						+ word.substring(i, word.length());
				String smw = bt.search(tmp);
				addToList(smw);
			}
		}
	}

    /*this method assumes one word has been added to either front, back or anywhere
     it removes by skipping one index of word at a time and it check if any similar word in found in the
   binarytree. if found, it adds to the linkedlist*/

	public void addedOne(String word, BinaryTree bt) {
		for (int i = 0; i < word.length(); i++) {
			String tmp = word.substring(0, i)
					+ word.substring(i + 1, word.length());
			String smw = bt.search(tmp);
			addToList(smw);
		}
	}

	public void similar_words(String input, BinaryTree bt) {

		start_time = System.nanoTime();
		relatedwords = new LinkedList<String>();
		similarOne(input, bt);
		replacedOne(input, bt);
		removedOne(input, bt);
		addedOne(input, bt);

		end_time = System.nanoTime();

		double difference = (end_time - start_time) / 1e6;

		String text = relatedwords.toString().replace("[", "") // remove the
																// right bracket
				.replace(",", "\n->") // remove the commas
				.replace("]", "") // remove the left bracket
				.trim();
		if (relatedwords.size() > 0) {
			System.out.println(input
					+ " not found but these are related words.");

			System.out.println("->"+text);
			System.out.println("Time used to generate is " + difference
					+ " milleseconds");

			System.out.println("number of lookups  " + "" + lookups);
			System.out.println();
		} else
			System.out.println("no word found");

		lookups = 0;
	}

       /* This methods adds to the linkedlist only the word that are found in the binarytree
       */
	public void addToList(String rword) {
                     lookups++;
		if (rword != null) 
		    relatedwords.add(rword);

		
	}
}
