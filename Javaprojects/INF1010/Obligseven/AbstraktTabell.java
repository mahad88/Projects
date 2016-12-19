

public interface AbstraktTabell <T> extends Iterable<T>{
	boolean setInn(T t ,int index);
  T inholder(int index);
}
