package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;
import ee.matti.pokerhand.Triple;

public class TripleRanker implements Ranker {

	@Override
	public boolean matches(Hand hand) {
		return hand.containsTriple();
	}
	
	@Override
	public int compare(Hand h1, Hand h2) {
		Triple t1 = h1.triple();
		Triple t2 = h2.triple();
		
		return t1.face().compareTo(t2.face());
	}

}
