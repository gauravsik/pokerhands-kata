package ee.matti.pokerhand;

public class TwoPairs {
	public final Pair firstPair;
	public final Pair secondPair;
	public final Card remainingCard;
	
	
	public TwoPairs(Pair pair1, Pair pair2, Card remainingCard) {
		super();
		this.firstPair = pair1;
		this.secondPair = pair2;
		this.remainingCard = remainingCard;
	}
}
