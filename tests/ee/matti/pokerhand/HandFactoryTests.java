package ee.matti.pokerhand;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import org.junit.Test;


public class HandFactoryTests {

	@Test
	public void test_create_hand_factory() {
		HandFactory hf = new HandFactory();
		assertThat(hf, is(hf));
	}
	
	@Test
	public void test_parse_suit() {
		Suit s = Suit.fromCode("H");
		assertThat(s, is(Suit.HEARTS));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_parse_unknown_suit_throws() {
		Suit.fromCode("UNKNOWN");
	}
	
	@Test
	public void test_parse_face() {
		Face f = Face.fromCode("T");
		assertThat(f, is(Face.TEN));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_parse_unknown_face_throws() {
		Face.fromCode("UNKNOWN");
	}
	
	@Test
	public void test_parse_ace_of_spades() {
		Card aceOfSpades = new Card("AS");
		assertThat(aceOfSpades.suit(), is(Suit.SPADES));
		assertThat(aceOfSpades.face(), is(Face.ACE));
	}
	
	@Test
	public void test_parse_ten_of_hearts() {
		Card aceOfSpades = new Card("TH");
		assertThat(aceOfSpades.suit(), is(Suit.HEARTS));
		assertThat(aceOfSpades.face(), is(Face.TEN));
	}
	
	@Test
	public void test_parse_full_hand() {
		Hand h = new HandFactory().parse("TH", "JH", "QH", "KH", "AH");
		assertThat(h.cards().size(), is(5));
		assertThat(h.cards(), hasItem(new Card(Suit.HEARTS, Face.TEN)));
	}
	
	@Test(expected=Exception.class)
	public void test_parse_unknown_card() {
		new HandFactory().parse("UU");
	}
}
