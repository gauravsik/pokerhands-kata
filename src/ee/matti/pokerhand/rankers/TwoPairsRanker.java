package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;
import ee.matti.pokerhand.combinations.TwoPairs;

public class TwoPairsRanker implements Ranker {

	@Override
	public boolean matches(Hand hand) {
		return hand.containsTwoPairs();
	}


	@Override
	public int compare(Hand h1, Hand h2) {
		TwoPairs pair1 = h1.pairs();
		TwoPairs pair2 = h2.pairs();
		
		// compare the strongest pairs
		int strongerCards = pair1.firstPair.compareTo(pair2.firstPair);
		if (strongerCards != 0)
			return strongerCards;
		
		// first pairs equal, compare weaker pairs
		int weakerCards = pair1.secondPair.compareTo(pair2.secondPair);
		if (weakerCards != 0)
			return weakerCards;
		
		// both pairs equal, compare highest card
		return pair1.remainingCard.compareTo(pair2.remainingCard);
	}
}
