package ee.matti.pokerhand;

public class Card implements Comparable<Card> {
	private final Suit suit;
	private final Face face;
	
	public Card(String cardDescription) {
		this.suit = Suit.fromCode(cardDescription.substring(cardDescription.length()-1));
		this.face = Face.fromCode(cardDescription.substring(0, cardDescription.length() -1));
	}
	
	public Card(Suit s, Face f) {
		this.suit = s;
		this.face = f;
	}

	public Suit suit() {
		return this.suit;
	}

	public Face face() {
		return this.face;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Card))
			return false;
		Card o = (Card) obj;
		return suit == o.suit() && face == o.face();
	}

	@Override
	public String toString() {
		return "<" + face + " of "+ suit + ">";
	}

	@Override
	public int compareTo(Card o) {
		return face.compareTo(o.face);
	}
}
