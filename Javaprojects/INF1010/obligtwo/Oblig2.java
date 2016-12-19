

/**
 * 
 * @author Mahad
 *
 */
public class Oblig2 {
	public static void main(String[] args) {
		Hylle<Bok> hyller = new Hylle<Bok>(100);
		Bok b = new Bok("narative of slaves", "fredrick doughlas");
		Bok b1 = new Bok("wilwal", "jafar abul");
		Bok b2 = new Bok("fugitive", "Per mat");
		Bok b3 = new Bok("die", "kevi Harts");
		Bok b4 = new Bok("audacity of hope", "barack Obama");
		Bok b5 = new Bok("My life", "Bill Clinton");

		hyller.setInn(b1, 92);
		hyller.setInn(b2, 32);
		hyller.setInn(b3, 37);
		hyller.setInn(b4, 81);
		hyller.setInn(b5, 14);

		hyller.taUt( 81);
		hyller.taUt( 14);

		testLedigPlass(true, hyller.erLedig(12));
		testSetInn(true, hyller.setInn(b1, 492));
		testtaUt(null, hyller.taUt(64));
		testHylleStorrelse(100, hyller.hentstorrelse());
		//testSammenObjekt(b3, hyller.taUt(b3, 37));

	}

	/**
	 * for teste hvis plass er ledig
	 * 
	 * @param assumption
	 *            noen man forvente
	 * @param expected
	 *            faktisk out av programm
	 */
	public static <T> void testLedigPlass(T assumption, T expected) {
		if (assumption.equals(expected)) {
			System.out.println("plass er ledig");
		} else
			System.out.println("plass er full");
	}

	/**
	 * for aa tester hvis objekt can settes in i et plass
	 * 
	 * @param assumption
	 *            noen man forvente
	 * @param expected
	 *            faktisk output ava program
	 */
	public static <T> void testSetInn(T assumption, T expected) {

		if (assumption.equals(expected)) {
			System.out.println("plass er innen i hylle storrelse");
		} else

			System.out.println("plass er ut av hylle storrelsen ");
	}

	/**
	 * test aa tester hvis objekt kan tas ut.
	 * 
	 * @param assumption
	 *            noen man forvente
	 * @param expected
	 *            faktisk uotput man forvente
	 */
	public static <T> void testtaUt(T assumption, T expected) {

		if (assumption == expected) {

			System.out.println("ingen objekt aa ta ut");
			return;
		} else

			System.out.println("Kan tas ut ");
	}

	/**
	 * teste hylle storrelse
	 * 
	 * @param assumption
	 *            noen man forvente
	 * @param expected
	 *            storrelse av den hylle
	 */
	public static <T> void testHylleStorrelse(T assumption, T expected) {

		if (assumption == expected) {
			System.out.println("Det er riktig hyller storrelsen");
			return;
		} else

			System.out.println("Det er fail hyller Storrelsen ");

	}

	

}
