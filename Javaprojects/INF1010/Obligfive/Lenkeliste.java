public class Lenkeliste<E extends Comparable<E>> {

	private Node forste = null;

	class Node {
		E data;
		Node neste;

		Node(E data) {
			this.data = data;
			neste = null;
		}

		public String toStirng() {
			return String.format(" " + data);
		}
	}

	public boolean tom() {

		return forste == null;
	}

	/**
	 * legge node paa foran siden
	 * 
	 * @param e
	 *            er generisks type
	 */
	public void leggTil(E e) {
		if (tom())
			forste = new Node(e);
		else {
			Node temp = new Node(e);
			temp.neste = forste;
			forste = temp;
		}

	}

	/**
	 * ta ut node med minst data i leksikografiske rekkefølge
	 * 
	 * @return E data av minste node
	 */
	public E fjernMinste() {

		Node minste = forste;
		Node temp = forste;
		Node fjern = forste;

		if (tom())
			return null;
		if (temp != null && temp.neste == null) {
			forste = null;
			fjern = temp;
			return fjern.data;
		}

		while (temp.neste != null) {
			if (minste.neste.data.compareTo(temp.neste.data) > 0) {
				minste = temp;
				minste.neste = temp.neste;
				temp = temp.neste;

			} else
				temp = temp.neste;
		}

		if (minste.neste.data.compareTo(forste.data) > 0) {
			fjern = forste;
			forste = forste.neste;
		} else {
			fjern = minste.neste;
			minste.neste = minste.neste.neste;
		}
		return fjern.data;

	}

	/**
	 * sjekk hvis input e i parameter har lik element i beholder
	 * 
	 * @param e
	 *            er generisks type element
	 * @return true hvis der finnes eller false hvis ikke
	 */
	public boolean inneholder(E e) {
		if (tom())
			return false;
		else {
			Node temp = forste;
			while (temp != null) {
				if (e.compareTo(temp.data) == 0)

					return true;
				else
					temp = temp.neste;
			}

			return false;
		}
	}

}
