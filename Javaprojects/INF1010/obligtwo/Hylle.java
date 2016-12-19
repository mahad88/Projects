

/**
 * 
 * @author Mahad
 *
 * @param <T>
 *            hylle er generisk class
 */
public class Hylle<T> implements GenHylle<T> {
	private int kapasitet;
	private int antall;
	private T[] antplass;

	@SuppressWarnings("unchecked")
	public Hylle(int kapasitet) {
		this.kapasitet = kapasitet;
		antplass = (T[]) new Object[kapasitet];
	}

	/**
	 * for sjekke hylle storrelse
	 * 
	 * @return hylle storresle
	 */

	public int hentstorrelse() {
		return antplass.length;
	}

	/**
	 * jekke hylle antall Objekt i hylle
	 * 
	 * @return antall objekt i hyller
	 */
	public int antallObjekt() {
		return antall;
	}

	/**
	 * for aa sette inn objekt i spesifisk plass
	 * 
	 * @param t
	 *            objekt som skal settes in
	 * @param i
	 *            plass objekt skal settes in
	 * @return true hvis objekt ble satt in
	 */

	public boolean setInn(T t, int i) {
		if (i > 0 && i <= antplass.length) {
			if (erLedig(i)) {
				antplass[i] = t;
				antall++;
				return true;
			} else {
				System.out.println("hylle er besatt i positon " + i);
				return true;
			}
		} else
			return false;

	}

	/**
	 * sjekke en plass er ledig
	 * 
	 * @param i
	 *            plass som sjekkes
	 * @return true hvis ledig
	 */
	public boolean erLedig(int i) {
		if (antplass[i] == null)
			return true;
		else
			return false;
	}

	/**
	 * ta objekt fra en plass
	 * 
	
	 * @param i
	 *            plass som objekt tas ut fra.
	 * @return objekt som tas ut.
	 */
	public T taUt( int i) {
		T temp = null;
		if (i > 0 && i <= antplass.length) {
			if (!erLedig(i)) {
				temp = antplass[i];
				antplass[i] = null;
				antall--;
				return temp;
			} else {
				System.out.println("position  " + i + " er tom");
				return temp;
			}
		} else {
			// System.out.println("position is out of range");
			return temp;
		}

	}

}
