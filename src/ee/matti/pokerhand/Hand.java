package ee.matti.pokerhand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
		List<Card> sorted = new ArrayList<Card>(cards);
		Collections.sort(sorted, new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				return o2.compareTo(o1);
			}
		});
		return sorted.get(0);
	}

	public Pair pair() {
		for (Face f : groupedByFaces.keySet()) {
			if (groupedByFaces.get(f).size() == 2) {
				Card c1 = groupedByFaces.get(f).get(0);
				Card c2 = groupedByFaces.get(f).get(1);
				return new Pair(c1, c2);
			}
		}
		throw new IllegalStateException("Hand does not contain any pairs: " + this.toString());
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
		List<Card> triple = null;
		List<Card> rest = new ArrayList<Card>();
		for (Face f : groupedByFaces.keySet()) {
			if (groupedByFaces.get(f).size() == 3) {
				triple = groupedByFaces.get(f);
			} else {
				rest.addAll(groupedByFaces.get(f));
			}
		}
		if (triple != null && rest.size() == 2) {
			return new Triple(triple.get(0), triple.get(1), triple.get(2), rest.get(0), rest.get(1));
		}
		throw new IllegalStateException("Requested triple when there is none");
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
		List<Card> sorted = cardSortedByFace();
		Card prev = sorted.remove(0);
		for (Card curr : sorted) {
			if (prev.suit() != curr.suit()
					|| curr.face().ordinal() - prev.face().ordinal() != 1)
				return false;
			prev = curr;
		}
		return true;
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
		List<Card> four = null;
		List<Card> rest = new ArrayList<Card>();
		for (Face f : groupedByFaces.keySet()) {
			if (groupedByFaces.get(f).size() == 4) {
				four = groupedByFaces.get(f);
			} else {
				rest.addAll(groupedByFaces.get(f));
			}
		}
		if (four != null && rest.size() == 1) {
			return new FourOfAKind(four.get(0), four.get(1), four.get(2), four.get(3), rest.get(0));
		}
		throw new IllegalStateException("Requested triple when there is none");
	}
}
