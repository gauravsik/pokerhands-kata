package ee.matti.pokerhand.rankers;

import ee.matti.pokerhand.Hand;

/**
 * An interface for different ranking rules of poker hands.
 * 
 * @author matti
 */
public interface Ranker {
	/**
	 * Determine if the provided hand matches for this ranking rule.
	 */
	boolean matches(Hand hand);

	/**
	 * Compare two hands provided that the both match this ranker.
	 */
	int compare(Hand h1, Hand h2);
}
