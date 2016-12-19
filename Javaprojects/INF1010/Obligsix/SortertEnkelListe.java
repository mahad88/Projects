

import java.util.Iterator;

public class SortertEnkelListe<T extends Comparable<T> & Lik> implements
		AbstraktSortertEnkelListe<T> {

	private Node forste;
	private int i;

	SortertEnkelListe() {
		i = 0;
	}

	class Node {
		T data;
		Node neste;
		Node forrige;

		Node(T data) {
			this.data = data;
		}

		public String toStirng() {
			return String.format(" " + data);
		}

	}
//sett in paa sortet maate
	@Override
	public void setInn(T t) {
		Node ny = new Node(t);
//sett inn  som forst
		if (forste == null) {
			forste = ny;
			i++;
			return;
			
		}
		Node temp = forste;
		//sett inn  som andre
		if (temp.neste == null) {
			if (temp.data.compareTo(t) < 0) {

				temp.neste = ny;
				i++;
				return;
			} else {
				ny.neste = temp;
				forste = ny;
				return;
			}
		}

		else {

			while (temp.neste != null) {

				if (temp.data.compareTo(t) > 0) {
					ny.neste = temp;
					forste = ny;
					i++;
					return;
				}

				else if (temp.data.compareTo(t) < 0
						&& temp.neste.data.compareTo(t) >= 0) {
					ny.neste = temp.neste;
					temp.neste = ny;
					i++;
					return;
				} else
					temp = temp.neste;
			}
			// sett in som sist
			if (temp.neste == null)
				temp.neste = ny;

		}
	}
//sjekke hvis navn er i paa beholder
	@Override
	public T inholder(String navn) {
		Node temp = forste;
		if (forste == null)
			return null;
		else {

			while (temp != null) {
				if (temp.data.samme(navn))

					return temp.data;
				else
					temp = temp.neste;
			}

			return null;
		}

	}

	@Override
	public Iterator<T> iterator() {

		return new ListIterator();
	}

	class ListIterator implements Iterator<T> {
		Node temp;

		ListIterator() {
			temp = forste;
		}

		@Override
		public boolean hasNext() {

			return temp != null;
		}

		@Override
		public T next() {
			T retur = temp.data;
			temp = temp.neste;
			return retur;
		}

	}

	public int geti() {
		return i;
	}
}
