

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchWords {
	/*
	 * This method generates a bad character shift array for use in the boyer
	 * moore search.
	 */
	public int[] bad_character_shift(String needle) {

		int[] badCharShift = new int[256];
		int last = needle.length() - 1;

		for (int i = 0; i < badCharShift.length; i++) {
			badCharShift[i] = -1;  
		}

		for (int i = 0; i < last; i++) {
			if (needle.charAt(i) == '_') {
				Arrays.fill(badCharShift, i);
			}
			badCharShift[needle.charAt(i)] = i;
		}
		return badCharShift;
	}

	/*
	 * This is the Boyer Moore algorithm.Its the bad character shift algorithm
	 * ment to handle character wildcard.The needle match is
	 * searched until the end of the haystack text.This means it will return all
	 * the matches. The method will return indexes where needle matches haystack
	 * and the word that matches the needle.
	 */

	public List<Integer> bmSearch(String needle, String haystack) {

		List<Integer> matches = new ArrayList<Integer>();

		if ((needle == null) || (haystack == null)
				|| (needle.length() > haystack.length()) || (needle.isEmpty())
				|| (haystack.isEmpty())) {
			return matches;
		}
		int[] bcs = bad_character_shift(needle);
		boolean hit = false;
		int last = needle.length() - 1;
		int maxOfSet = haystack.length() - needle.length();
		int offset = 0; // haystack index moves from left to right
		int index = 0; // needle index moves from right to left
		
		/* iterates from left to the right til the end of haystack */
		while (offset <= maxOfSet) {
			index = last;
			hit = false;

			// if their is match, move the needle left through the needle

			while ((needle.charAt(index) == haystack.charAt(offset + index))
					|| ((needle.charAt(index) == '_'))) {

				index--; // needle index moves to left
				if (index < 0) {
					/*
					 * no any mismatches on the needle upon iteration . add
					 * offset to matches
					 */
					matches.add(offset);
					hit = true;
					break;
				}
			}
			if (hit) {
				offset += needle.length();// increase the offset to the end of prev need.
			} else {
				// if no match move needle to right
				char nc = haystack.charAt(offset + last);

				offset += last - bcs[nc];
			}
		}
		return matches;
	}

	
}
