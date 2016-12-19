

/**
 * 
 * @author Mahad
 *
 * @param <T>
 *            genHylle er generisk interface
 */
public interface GenHylle<T> {
	/**
	 * for aa sjekke hylle storrelse
	 * 
	 * @return hylle storrelse
	 */
	int hentstorrelse();

	/**
	 * for aa sette inn objekt i spesifisk plass
	 * 
	 * @param t
	 *            objekt som skal settes in
	 * @param i
	 *            plass objekt skal settes in
	 * @return true hvis objekt ble satt in
	 */
	boolean setInn(T t, int i);

	/**
	 * for aa sjekke plass status
	 * 
	 * @param i
	 *            plass som sjekkes
	 * @return true hvis plass er ledig
	 */
	boolean erLedig(int i);

	/**
	 * for aa ta ut objekt
	 * 
	
	 * @param i
	 *            plass objekt tas ut fra
	 * @return objekt som tas ut
	 */
	T taUt(int i);

}
