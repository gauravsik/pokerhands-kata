package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.FourOfAKind;
import ee.matti.pokerhand.Hand;

public class FourOfAKindRanker implements Ranker {

	@Override
	public boolean matches(Hand hand) {
		return hand.containsFourOfAKind();
	}
	
	@Override
	public int compare(Hand h1, Hand h2) {
		FourOfAKind f1 = h1.fourOfAKind();
		FourOfAKind f2 = h2.fourOfAKind();
		
		return f1.face().compareTo(f2.face());
	}

}
