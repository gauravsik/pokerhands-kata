package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;
import ee.matti.pokerhand.combinations.Pair;

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
		Pair pair1 = h1.pair();
		Pair pair2 = h2.pair();
		
		if (pair1.compareTo(pair2) != 0)
			return pair1.compareTo(pair2);
		
		for (int i = 0; i<pair1.remaining.size(); i++) {
			int r = pair1.remaining.get(i).compareTo(pair2.remaining.get(i));
			if (r != 0)
				return r;
		}
		return 0;
	}
}
