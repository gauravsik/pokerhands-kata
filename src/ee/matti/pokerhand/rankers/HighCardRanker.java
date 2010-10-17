package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;

public class HighCardRanker implements Ranker {

	@Override
	public boolean matches(Hand hand) {
		return true; // every hand has a high card
	}

	public int compare(Hand h1, Hand h2) {
		return h1.highCard().compareTo(h2.highCard());
	}
	
}
