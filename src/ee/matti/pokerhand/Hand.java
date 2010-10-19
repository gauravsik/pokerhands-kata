package ee.matti.pokerhand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ee.matti.pokerhand.combinations.FourOfAKind;
import ee.matti.pokerhand.combinations.Pair;
import ee.matti.pokerhand.combinations.Triple;
import ee.matti.pokerhand.combinations.TwoPairs;


public class Hand implements Comparable<Hand> {
	private final List<Card> cards;
	private final Map<Face, List<Card>> groupedByFaces;
	
	public Hand(List<Card> cards) {
		this.cards = cards;
		groupedByFaces = Collections.unmodifiableMap(groupByFaces());
	}
	
	@Override
	public int compareTo(Hand o) {
		return new PokerHandComparator().compare(this, o);
	}

	public List<Card> cards() {
		return Collections.unmodifiableList(cards);
	}

	public boolean containsPair() {
		return containsGroupOfSize(2);
	}
	
	public Card highCard() {
		List<Card> sorted = cardSortedByFace();
		return sorted.get(sorted.size() - 1);
	}

	public Pair pair() {
		Tuple<List<Card>,List<Card>> tuple = findCombinationOf(2);
		return new Pair(tuple.first.get(0), tuple.first.get(1), tuple.second);
	}
	
	private Map<Face,List<Card>> groupByFaces() {
		HashMap<Face, List<Card>> r = new HashMap<Face, List<Card>>();
		for (Card c : cards) {
			if (!r.containsKey(c.face()))
				r.put(c.face(), new ArrayList<Card>());
			r.get(c.face()).add(c);
		}
		return r;
	}
	
	public String toString() {
		return "Hand(" + cards + ")";
	}

	public boolean containsTwoPairs() {
		boolean sawOnePair = false;
		for (Face f : groupedByFaces.keySet()) {
			if (groupedByFaces.get(f).size() == 2) {
				if (!sawOnePair)
					sawOnePair = true;
				else 
					return true;
			}
		}
		return false;
	}

	/**
	 * Return two pairs present in the hand if there are exactly two present. If 
	 * there not exactly two pairs, throws an IllegalStateException
	 * @return
	 */
	public TwoPairs pairs() {
		Pair pairs[] = new Pair[2];
		List<Card> remaining = new ArrayList<Card>();
		
		for (Face f : groupedByFaces.keySet()) {
			if (groupedByFaces.get(f).size() == 2) {
				if (pairs[0] == null) {
					pairs[0] = new Pair(groupedByFaces.get(f));
				} else { 
					pairs[1] = new Pair(groupedByFaces.get(f));
				}
			} else {
				remaining.addAll(groupedByFaces.get(f));
			}
		}
		if (pairs[1] != null && remaining.size() == 1) {
			Arrays.sort(pairs, new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					return o2.compareTo(o1); // strongest first
				}
			});
			return new TwoPairs(pairs[0], pairs[1], remaining.get(0));
		}
		throw new IllegalStateException("Requested two pairs when there are not exactly two pairs");
	}

	public boolean containsTriple() {
		return containsGroupOfSize(3);
	}

	private boolean containsGroupOfSize(int size) {
		for (Face f : groupedByFaces.keySet()) {
			if (groupedByFaces.get(f).size() == size)
				return true;
		}
		return false;
	}

	public Triple triple() {
		Tuple<List<Card>,List<Card>> t = findCombinationOf(3);
		return new Triple(t.first.get(0), t.first.get(1), t.first.get(2), t.second.get(0), t.second.get(1));	
	}

	public boolean isStraight() {
		List<Card> sorted = cardSortedByFace();
		Card prev = sorted.remove(0);
		for (Card curr : sorted) {
			if (curr.face().ordinal() - prev.face().ordinal() != 1)
				return false;
			prev = curr;
		}
		return true;
	}

	private List<Card> cardSortedByFace() {
		List<Card> sorted = new ArrayList<Card>(cards);
		Collections.sort(sorted);
		return sorted;
	}

	public boolean isStraightFlush() {
		return isStraight() && isFlush();
	}

	public boolean isFlush() {
		Suit suitOfFirst = cards.get(0).suit();
		for (Card c : cards)
			if (c.suit() != suitOfFirst)
				return false;
		return true;
	}

	public boolean containsFourOfAKind() {
		return containsGroupOfSize(4);
	}

	public FourOfAKind fourOfAKind() {
		Tuple<List<Card>,List<Card>> t = findCombinationOf(4);
		return new FourOfAKind(t.first, t.second.get(0));
	}
	
	public static <F,S> Tuple<F,S> tuple(F f, S s) {
		return new Tuple<F,S>(f, s);
	}

	private Tuple<List<Card>,List<Card>> findCombinationOf(int numberOfFaces) {
		List<Card> pair = null;
		List<Card> rest = new ArrayList<Card>();
		for (Face f : groupedByFaces.keySet()) {
			if (groupedByFaces.get(f).size() == numberOfFaces) {
				pair = groupedByFaces.get(f);
			} else {
				rest.addAll(groupedByFaces.get(f));
			}
		}
		if (pair != null && rest.size() == (5 - numberOfFaces)) {
			Collections.sort(rest);
			Collections.reverse(rest);
			return tuple(pair, rest);
		}
		throw new IllegalStateException("Hand does not contain any pairs: " + this.toString());
	}
}
