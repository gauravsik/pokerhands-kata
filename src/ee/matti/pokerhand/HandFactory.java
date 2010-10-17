package ee.matti.pokerhand;

import java.util.ArrayList;
import java.util.List;

public class HandFactory {

	public Hand parse(String... cards) {
		List<Card> result = new ArrayList<Card>();
		for (String cardDescription : cards) 
			result.add(new Card(cardDescription));
		
		return new Hand(result);
	}

}
