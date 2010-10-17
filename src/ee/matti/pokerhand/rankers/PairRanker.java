package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;

/**
 * A ranker for recognizing pairs.
 * 
 * @author matti
 */
public class PairRanker implements Ranker {

	@Override
	public boolean matches(Hand hand) {
		return hand.containsPair();
	}

	@Override
	public int compare(Hand h1, Hand h2) {
		return h1.pair().compareTo(h2.pair());
	}

}
