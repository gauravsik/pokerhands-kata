/**
 * 
 */
package ee.matti.pokerhand.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsNegative extends TypeSafeMatcher<Number> {
	@Override
	protected boolean matchesSafely(Number item) {
		return item.doubleValue() < 0;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("negative");
	}

	@Factory
	public static <T> Matcher<? super Number> negative() {
		return new IsNegative();
	}
}