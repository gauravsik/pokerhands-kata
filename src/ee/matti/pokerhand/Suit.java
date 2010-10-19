package ee.matti.pokerhand;

public enum Suit {
	DIAMONDS("D"),
	CLUBS("C"),
	HEARTS("H"),
	SPADES("S");
	
	private final String code;
	
	Suit(String c) {
		code = c;
	}
	
	public static Suit fromCode(String c) {
		for (Suit s : values()) {
			if (s.code.equals(c))
				return s;
		}
		throw new IllegalArgumentException("No suite found for code [" + c + "]");
	}
}
