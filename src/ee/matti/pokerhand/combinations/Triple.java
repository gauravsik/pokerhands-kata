package ee.matti.pokerhand.combinations;

import ee.matti.pokerhand.Card;
import ee.matti.pokerhand.Face;
import junit.framework.Assert;

public class Triple {
	public final Card card1;
	public final Card card2;
	public final Card card3;
	
	public final Card remaining1;
	public final Card remaining2;
	
	public Triple(Card card1, Card card2, Card card3, Card remaining1, Card remaining2) {
		super();
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
		this.remaining1 = remaining1;
		this.remaining2 = remaining2;
		
		Assert.assertTrue(card1.face() == card2.face() && card2.face() == card3.face());
	}
	
	public Face face() {
		return card1.face();
	}
}
