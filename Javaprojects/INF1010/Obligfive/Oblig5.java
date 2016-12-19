public class Oblig5 {
	public static void main(String[] args) {
		Lenkeliste<String> beholder = new Lenkeliste<String>();
		/**
		 * sett inn navn paa beholder
		 */

		beholder.leggTil("kamaa");
		beholder.leggTil("veaak");
		beholder.leggTil("hendry");
		beholder.leggTil("alvez");
		beholder.leggTil("nobel");
		
		beholder.fjernMinste();
		
		System.out.println("finnes det	" + beholder.inneholder("alvez"));

	}
}
