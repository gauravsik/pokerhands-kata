package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;

public class StraightFlushRanker extends HighCardRanker implements Ranker {

	@Override
	public boolean matches(Hand hand) {
		return hand.isStraightFlush();
	}
}
