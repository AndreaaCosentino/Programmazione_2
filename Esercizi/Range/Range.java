import java.util.*;

public class Range implements Iterable<Integer>{
	private final int from,to,step;

	public Range(int from, int to, int step){
		if(step == 0) throw new IllegalArgumentException("Step dev'essere diverso 0");
		this.from = from;
		this.to = to;
		this.step = step;
	}

	public Range(int to, int step){
		this(0,to,step);
	}

	public Range(int to){
		this(to, to > 0 ? +1 : -1);
	}

	@Override
	public Iterator<Integer> iterator(){
		return new Iterator<Integer>(){
			int next = from;
			boolean hasNext = step > 0 ? from < to : from > to;
			@Override
			public boolean hasNext(){
				if(!hasNext){
					next += step;
					hasNext = from < to ? next < to : next > to;
				}
				return hasNext;
			}

			@Override
			public Integer next(){
				if(!hasNext()) throw new NoSuchElementException();
				hasNext = false;
				return next;
			}
		};
	}
}