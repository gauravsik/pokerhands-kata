package ee.matti.pokerhand;

import junit.framework.Assert;

public class FourOfAKind {
	public final Card card1;
	public final Card card2;
	public final Card card3;
	public final Card card4;
	
	public final Card remaining;

	public FourOfAKind(Card card1, Card card2, Card card3, Card card4, Card remaining) {
		super();
		this.card1 = card1;
		this.card2 = card2;
		this.card3 = card3;
		this.card4 = card4;
		this.remaining = remaining;
		
		Assert.assertTrue(card1.face() == card2.face() 
				&& card2.face() == card3.face() 
				&& card3.face() == card4.face());
	}
	
	public Face face() {
		return card1.face();
	}
}
