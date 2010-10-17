package ee.matti.pokerhand;

import java.util.List;

import junit.framework.Assert;

public class Pair {
	public final Card card1;
	public final Card card2;
	
	public Pair(Card c1, Card c2) {
		this.card1 = c1;
		this.card2 = c2;
		
		Assert.assertEquals(card1.face(), card2.face());
	}
	
	public Pair(List<Card> cards) {
		this(cards.get(0), cards.get(1));
		Assert.assertTrue(cards.size() == 2);
	}

	public Face face() {
		return card1.face();
	}

	public int compareTo(Pair pair) {
		return this.face().compareTo(pair.face());
	}
	
	public String toString() {
		return "[" + card1 + "," + card2 + "]";
	}
}
