package ee.matti.pokerhand;

import static ee.matti.pokerhand.matchers.IsNegative.negative;
import static ee.matti.pokerhand.matchers.IsPositive.positive;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import ee.matti.pokerhand.combinations.TwoPairs;
import ee.matti.pokerhand.rankers.FlushRanker;
import ee.matti.pokerhand.rankers.FourOfAKindRanker;
import ee.matti.pokerhand.rankers.HighCardRanker;
import ee.matti.pokerhand.rankers.PairRanker;
import ee.matti.pokerhand.rankers.StraightFlushRanker;
import ee.matti.pokerhand.rankers.TripleRanker;
import ee.matti.pokerhand.rankers.TwoPairsRanker;

public class HandRankerTests {
	
	@Test
	public void should_match_high_card() throws Exception {
		Hand h = parse("2H", "AH");
		assertThat(new HighCardRanker().matches(h), is(true));
	}
	
	@Test
	public void ace_should_be_bigger_than_two() {
		Card ace = new Card("AS");
		Card two = new Card("2S");
		
		assertThat(ace.compareTo(two), is(positive()));
	}

	@Test
	public void ace_shoud_beat_two() throws Exception {
		Hand twoOfHearts = parse("2H");
		Hand aceOfHearts = parse("AH");
		
		assertThat(new HighCardRanker().compare(twoOfHearts, aceOfHearts), is(negative()));
	}
	
	@Test
	public void should_not_find_pair() throws Exception {
		Hand h = parse("2H", "3S", "4D");
		assertThat(new PairRanker().matches(h), is(false));
	}
	
	@Test(expected=IllegalStateException.class)
	public void asking_for_pair_when_none_should_throw() {
		Hand h = parse("2H", "3D", "5S", "6H", "7D");
		h.pair();
	}
	
	@Test
	public void test_pair_matches() throws Exception {
		Hand h = parse("2H", "2S");
		assertThat(new PairRanker().matches(h), is(true));
	}
	
	@Test
	public void test_two_pairs_match() {
		Hand h = parse("2H", "2S", "3H", "3S", "4D");
		
		assertThat(new TwoPairsRanker().matches(h), is(true));
	}
	
	@Test
	public void fetching_two_pairs() {
		Hand h = parse("2H", "2S", "3H", "3S", "4D");
		TwoPairs pairs = h.pairs();
		
		assertThat(pairs.firstPair.face(), is(Face.THREE));
	}
	
	@Test(expected=IllegalStateException.class)
	public void fetching_two_pairs_throws_if_not_present() {
		Hand h = parse("2H", "3S", "4H", "5S", "6D");
		h.pairs();
	}
	
	@Test
	public void test_matching_a_triple() {
		Hand h = parse("2S", "2D", "2C", "3S", "4D");
		assertThat(new TripleRanker().matches(h), is(true));
	}
	
	@Test(expected=IllegalStateException.class)
	public void asking_a_triple_throws_when_none_present() {
		Hand h = parse("2S", "2D", "7C", "3S", "4D");
		h.triple();
	}
	
	@Test(expected=IllegalStateException.class)
	public void asking_four_throws_when_none_present() {
		Hand h = parse("2S", "2D", "7C", "3S", "4D");
		h.fourOfAKind();
	}
	
	@Test
	public void test_matching_a_flush() {
		Hand h = parse("2S", "4S", "6S", "8S", "AS");
		assertThat(new FlushRanker().matches(h), is(true));
	}
	
	@Test
	public void test_matching_four_of_a_kind() {
		Hand h = parse("2S", "2D", "2C", "2H", "AS");
		assertThat(new FourOfAKindRanker().matches(h), is(true));
	}
	
	@Test
	public void test_matching_straight_flush() {
		Hand h = parse("2S", "3S", "4S", "5S", "6S");
		assertThat(new StraightFlushRanker().matches(h), is(true));
	}
	
	private Hand parse(String... defs) {
		return new HandFactory().parse(defs);
	}
	
}
