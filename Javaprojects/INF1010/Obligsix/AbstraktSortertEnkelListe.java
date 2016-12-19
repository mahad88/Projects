

public interface AbstraktSortertEnkelListe<T extends Comparable<T> & Lik>
		extends Iterable<T> {
	void setInn(T t);

	T inholder(String navn);

}
