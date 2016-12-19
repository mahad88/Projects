
/**
 * 
 * @author Mahad
 *
 */
public interface TilUtlaan {
	/**
	 * utlaaner objekt til noe
	 * 
	 * @param navn
	 *            er laaner identifasjon
	 * @return hvis objekt er laant ut.
	 */
	boolean utlaan(String navn);

	/**
	 * Levere objekt tilbake
	 * 
	
	 * @return navnet av laaner som levere tilbake objekt
	 */
	boolean levereTilbake();
}
