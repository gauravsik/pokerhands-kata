package ee.matti.pokerhand.combinations;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import ee.matti.pokerhand.Card;
import ee.matti.pokerhand.Face;

public class Pair {
	public final Card card1;
	public final Card card2;
	
	public final List<Card> remaining;
	
	public Pair(Card c1, Card c2, List<Card> remaining) {
		this.card1 = c1;
		this.card2 = c2;
		
		this.remaining = remaining;
		
		Assert.assertEquals(card1.face(), card2.face());
	}
	
	public Pair(List<Card> cards) {
		this(cards.get(0), cards.get(1), new ArrayList<Card>());
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
