package ee.matti.pokerhand;

public class Tuple<F,S> {
	public final F first;
	public final S second;
	public Tuple(F f, S s) {
		first = f;
		second = s;
	}
}