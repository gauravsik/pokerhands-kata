package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;
import ee.matti.pokerhand.Triple;

public class HouseRanker implements Ranker {

	@Override
	public boolean matches(Hand hand) {
		return hand.containsPair() && hand.containsTriple();
	}

	@Override
	public int compare(Hand h1, Hand h2) {
		Triple t1 = h1.triple();
		Triple t2 = h2.triple();
		
		int tripleDifference = t1.face().compareTo(t2.face());
		if (tripleDifference != 0)
			return tripleDifference;
		
		return h1.pair().compareTo(h2.pair());
	}
	
}
