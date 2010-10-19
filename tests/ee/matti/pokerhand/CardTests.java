package ee.matti.pokerhand;

import static ee.matti.pokerhand.Face.ACE;
import static ee.matti.pokerhand.Suit.SPADES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.Test;

import ee.matti.pokerhand.combinations.Pair;

public class CardTests {
	@Test
	public void equal_cards_should_be_equal() {
		Card c1 = new Card(SPADES, ACE);
		Card c2 = new Card("AS");
		
		assertThat(c1.equals(c2), is(true));
		assertThat(c1.equals(null), is(false));
		assertThat(c1.equals(new Object()), is(false));
	}
	
	@Test
	public void card_should_override_tostring() {
		Card c = new Card(SPADES, ACE);
		
		assertThat(c.toString(), is("<ACE of SPADES>"));
	}
	
	@Test
	public void pair_should_override_tostring() {
		Pair p = new Pair(new Card("AS"), new Card("AD"), 
				Arrays.asList(new Card("2H"), new Card("2D"), new Card("2S")));
		assertThat(p.toString(), is("[<ACE of SPADES>,<ACE of DIAMONDS>]"));
	}
}
