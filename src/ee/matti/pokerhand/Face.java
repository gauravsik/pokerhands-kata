package ee.matti.pokerhand;

public enum Face {
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("T"),
	JACK("J"),
	QUEEN("Q"),
	KING("K"),
	ACE("A");
	
	private String code;
	
	Face(String c) { 
		code = c; 
	}
	
	public static Face fromCode(String c) {
		for (Face f : values())
			if (f.code.equals(c))
				return f;
		throw new IllegalArgumentException("Unknown card face [" + c + "]");
	}
}
