package ee.matti.pokerhand.acceptance;

import static ee.matti.pokerhand.matchers.IsNegative.negative;
import static ee.matti.pokerhand.matchers.IsPositive.positive;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import ee.matti.pokerhand.Hand;
import ee.matti.pokerhand.HandFactory;

public class PokerHandRankingTests {
	private static final int EQUAL = 0;

	@Test
	public void equal_hands_are_equal() {
		Hand h1 = parse("2H", "3H", "TH", "QH", "KH");
		Hand h2 = parse("2D", "3D", "TD", "QD", "KD");
		
		assertThat(h1.compareTo(h2), is(EQUAL));
	}
	
	@Test
	public void ace_should_beat_king() {
		Hand ace = parse("2H", "3D", "TH", "QD", "AH");
		Hand king = parse("2D", "3H", "TD", "QH", "KD");
		
		assertThat(king.compareTo(ace), is(negative()));
	}
	
	@Test
	public void pair_is_stronger_than_high_card() {
		Hand stronger = parse("2H", "2C", "TH", "QH", "KH");
		Hand weaker = parse("2D", "3D", "TH", "QD", "AD");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void higher_pair_should_be_stronger() {
		Hand stronger = parse("3H", "3C", "TH", "QH", "KH");
		Hand weaker = parse("2D", "2S", "TD", "QD", "AD");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void equal_pairs_are_equal() {
		Hand stronger = parse("2H", "2C", "TH", "QH", "KH");
		Hand weaker = parse("2D", "2S", "TD", "QD", "KD");
		
		assertThat(stronger.compareTo(weaker), is(EQUAL));
	}
	
	@Test
	public void high_card_beats_when_pairs_equal() {
		Hand stronger = parse("2H", "2C", "TH", "QH", "AH");
		Hand weaker = parse("2D", "2S", "TD", "QD", "KD");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void highest_nonpair_card_beats_when_pairs_equal() {
		Hand stronger = parse("TH", "TC", "2H", "3H", "6H");
		Hand weaker = parse("TD", "TS", "3D", "4D", "5D");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void two_pairs_should_beat_one_pair() {
		Hand twoPairs = parse("2H", "2C", "3H", "3D", "KH");
		Hand onePair = parse("AD", "AS", "TC", "QD", "KD");
		
		assertThat(twoPairs.compareTo(onePair), is(positive()));
	}
	
	@Test
	public void higher_pair_wins_from_two_pairs() {
		Hand stronger = parse("2D", "2S", "4C", "4D", "KD");
		Hand weaker = parse("2H", "2C", "3H", "3D", "KH");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void higher_pair_wins_if_stronger_pairs_equal() {
		Hand stronger = parse("4D", "4S", "6C", "6D", "KD");
		Hand weaker = parse("3H", "3C", "6H", "6S", "KH");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void high_card_wins_if_two_pairs_equal() {
		Hand stronger = parse("5D", "5S", "6C", "6D", "3D");
		Hand weaker = parse("5H", "5C", "6H", "6S", "2H");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void triple_should_beat_a_pair() {
		Hand triple = parse("2C", "2S", "2D", "3S", "4S");
		Hand pair = parse("6C", "6S", "7D", "8S", "9S");
		
		assertThat(triple.compareTo(pair), is(positive()));
	}
	
	@Test
	public void higher_triple_should_win() {
		Hand stronger = parse("4C", "4S", "4D", "3S", "6S");
		Hand weaker = parse("3C", "3S", "3D", "8S", "9S");
		
		assertThat(weaker.compareTo(stronger), is(negative()));
	}
	
	@Test
	public void straight_should_beat_triple() {
		Hand straight = parse("2C", "3D", "4C", "5D", "6C");
		Hand triple = parse("3C", "3S", "3D", "8S", "9S");
		
		assertThat(straight.compareTo(triple), is(positive()));
	}
	
	@Test
	public void higher_straight_should_win() {
		Hand stronger = parse("3C", "4D", "5C", "6D", "7C");
		Hand weaker = parse("2C", "3D", "4C", "5D", "6C");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void flush_should_beat_straight() {
		Hand flush = parse("2S", "3S", "4S", "5S", "6S");
		Hand straight = parse("2C", "3D", "4C", "5D", "6C");
		
		assertThat(flush.compareTo(straight), is(positive()));
	}
	
	@Test
	public void higher_flush_should_win() {
		Hand stronger = parse("3H", "4H", "9H", "5H", "6H");
		Hand weaker = parse("2S", "3S", "8S", "5S", "6S");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void full_house_should_beat_flush() {
		Hand flush = parse("2S", "3S", "8S", "5S", "6S");
		Hand house = parse("2C", "2D", "2H", "5D", "5H");
		
		assertThat(flush.compareTo(house), is(negative()));
	}
	
	@Test
	public void higher_house_should_win() {
		Hand higher = parse("3C", "3D", "3H", "5S", "5C");
		Hand lower = parse("2C", "2D", "2H", "5D", "5H");
		
		assertThat(higher.compareTo(lower), is(positive()));
	}
	
	@Test
	public void higher_pair_beats_lower_pair_in_house() {
		Hand higher = parse("2C", "2D", "2H", "6D", "6H");
		Hand lower = parse("2C", "2D", "2H", "5D", "5H");
		
		assertThat(higher.compareTo(lower), is(positive()));
	}
	
	@Test
	public void four_of_a_kind_shoud_beat_full_house() {
		Hand house = parse("2C", "2D", "2H", "6D", "6H");
		Hand four = parse("3C", "3D", "3H", "3S", "2S");
		
		assertThat(four.compareTo(house), is(positive()));
	}
	
	@Test
	public void higher_four_should_win() {
		Hand stronger = parse("3C", "3D", "3H", "3S", "2S");
		Hand weaker = parse("2C", "2D", "2H", "2S", "6H");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	@Test
	public void straight_flush_should_beat_four() {
		Hand straightFlush = parse("3H", "4H", "5H", "6H", "7H");
		Hand four = parse("2C", "2D", "2H", "2S", "6H");
		
		assertThat(straightFlush.compareTo(four), is(positive()));
	}
	
	@Test
	public void higher_straight_flush_should_win() {
		Hand stronger = parse("3H", "4H", "5H", "6H", "7H");
		Hand weaker = parse("2S", "3S", "4S", "5S", "6S");
		
		assertThat(stronger.compareTo(weaker), is(positive()));
	}
	
	private Hand parse(String... cards) {
		HandFactory hf = new HandFactory();
		return hf.parse(cards);
	}
}
