import java.util.*;

public class FilterIterator<T> implements Iterator<T> {
  
  private final Iterator<T> source;
  private final Predicate<T> filter;

  private T next = null;
  private boolean hasNext = false;

  public FilterIterator(final Iterator<T> source, Predicate<T> filter) {
    this.source = source;
    this.filter = filter;
  }

  @Override
  public boolean hasNext() {
    while(!hasNext && source.hasNext()){
      next = source.next();
      hasNext = filter.test(next);
    }
    return hasNext;
  }

  @Override
  public T next() {
    if(!hasNext()) throw new NoSuchElementException();
    hasNext = false;
    return next;
  }

}