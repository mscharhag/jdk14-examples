# jdk14-examples

Java 14: Looking at the updated switch statement

JDK 14 released in March 2020 comes with an updated version of the switch statement.
This has been a preview feature in JDK 12 and JDK 13.

To see the difference, let's look at a simple example.
Assume we want to compute the daily working time based on a DayOfWeek enum.

With the old way of using the switch statement, our solution might look like this:

DayOfWeek day = ...
float expectedWorkingTime;

switch (day) {
	case MONDAY:
	case TUESDAY:
	case WEDNESDAY:
	case THURSDAY:
		expectedWorkingTime = 8f;
		break;
	case FRIDAY:
		expectedWorkingTime = 6;
		break;
	default:
		expectedWorkingTime = 0f;
}

With the new switch statement (or expression) we can rewrite our example like this:

DayOfWeek day = ...

final float expectedWorkingTime = switch (day) {
	case MONDAY, TUESDAY, WEDNESDAY, THURSDAY -> 8f;
	case FRIDAY -> 6f;
	default -> 0f;
};

So, what's new:
- 	The switch keyword can be used as expression and return a value.
	In this example the value returned by the switch is assigned to expectedWorkingTime.
	Note that this allows us to make expectedWorkingTime final which was not possible in the previous solution.
- 	A case statement can contain multiple values, separated by comma.
-	In the case statement : is replaced with ->
-	When using the arrow (->) syntax, no break keyword is required


The new yield statement

In the previous example we return a simple value on the right side of the arrow (->).
But maybe we need to compute this value first, for which we might need a few extra lines of code.

For example:

final float expectedWorkingTime = switch (day) {
	case MONDAY, TUESDAY, WEDNESDAY, THURSDAY -> {
		if (isFullTimeEmployee) {
			yield 8;
		}
		yield 4;
	}
	case FRIDAY -> 6f;
	default -> 0f;
};

Here we use a code block in the first case statement to determine the working time.
With the new yield statement we return a value from a case block (like using return in methods)
