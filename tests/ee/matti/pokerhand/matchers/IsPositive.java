/**
 * 
 */
package ee.matti.pokerhand.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsPositive extends TypeSafeMatcher<Number> {
	@Override
	protected boolean matchesSafely(Number item) {
		return item.doubleValue() > 0;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("positive");
	}
	
	public static <T> Matcher<? super Number> positive() {
		return new IsPositive();
	}
}