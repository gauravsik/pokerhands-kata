package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;

public class StraightRanker extends HighCardRanker implements Ranker {

	@Override
	public boolean matches(Hand hand) {
		return hand.isStraight();
	}
}
