package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;

public class StraightFlushRanker implements Ranker {

	@Override
	public boolean matches(Hand hand) {
		return hand.isStraightFlush();
	}

	
	@Override
	public int compare(Hand h1, Hand h2) {
		return h1.highCard().compareTo(h2.highCard());
	}
}
