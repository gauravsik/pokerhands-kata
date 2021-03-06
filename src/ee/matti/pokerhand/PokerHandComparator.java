package ee.matti.pokerhand;

import ee.matti.pokerhand.rankers.FlushRanker;
import ee.matti.pokerhand.rankers.FourOfAKindRanker;
import ee.matti.pokerhand.rankers.HighCardRanker;
import ee.matti.pokerhand.rankers.HouseRanker;
import ee.matti.pokerhand.rankers.PairRanker;
import ee.matti.pokerhand.rankers.Ranker;
import ee.matti.pokerhand.rankers.StraightFlushRanker;
import ee.matti.pokerhand.rankers.StraightRanker;
import ee.matti.pokerhand.rankers.TripleRanker;
import ee.matti.pokerhand.rankers.TwoPairsRanker;

public class PokerHandComparator {
	private Ranker rankers[];
	private Ranker defaultRanker = new HighCardRanker();
	
	public PokerHandComparator() {
		rankers = new Ranker[] {
				new StraightFlushRanker(),
				new FourOfAKindRanker(),
				new HouseRanker(),
				new FlushRanker(),
				new StraightRanker(),
				new TripleRanker(),
				new TwoPairsRanker(),
				new PairRanker()
		};
	}
	
	public int compare(Hand h1, Hand h2) {
		Ranker r = findMatchingRanker(h1, h2);
		
		if (r.matches(h1) && r.matches(h2)) {
			return r.compare(h1, h2);
		}
		else if (r.matches(h1)) {
			return 1;
		} 
		else {
			return -1;
		}
	}

	private Ranker findMatchingRanker(Hand h1, Hand h2) {
		for (Ranker r : rankers) {
			if (r.matches(h1) || r.matches(h2))
				return r;
		}
		return defaultRanker;
	}
}
