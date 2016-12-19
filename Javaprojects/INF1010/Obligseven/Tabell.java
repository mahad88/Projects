

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Tabell<T> implements AbstraktTabell<T> {
	private T[] tabell;
	private int total ;

	
	@SuppressWarnings("unchecked")
	Tabell(int antall) {
		total = 0;
		tabell = (T[]) new Object[antall];

	}

	// sett t paa en gitt plass
	@Override
	public boolean setInn(T t, int index) {
		if (index >=0 && index < tabell.length) {
			if (tabell[index] == null) {
				tabell[index] = t;
				total++;
				
				return true;
			} else
				return false;
		}
		return false;

	}
// sjekke hvis T er paa en plass
	@Override
	public T inholder(int index) {
		return tabell[index];
		
	}

	@Override
	public Iterator<T> iterator() {

		return new listIterator();
	}

	class listIterator implements Iterator<T> {
		 int count ;

		listIterator() {
			count=0;
		}

		public boolean hasNext() {
 
			return count <hentstorrelse() ;

		}

		@Override
		public T next() {
			
			if (hasNext())
				return tabell[count++];
				
			
			else	
			throw new NoSuchElementException();
		}

	}

	public int hentstorrelse() {
		return tabell.length;
	}
	@SuppressWarnings("rawtypes")
	public Tabell hentTabell(){
		return this;}

}




