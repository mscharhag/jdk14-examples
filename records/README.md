Java records

JEP 359, available as preview feature in JDK 14, introduces records to Java.
https://openjdk.java.net/jeps/359

Records are an easy way to model 'plain data' aggregates.

A simple Range record looks like this:

record Range(int from, int to) {}

A record definition is literally the same as a final class with
- immutable fields
- public accessors
- a constructor
- implementations for equals(), hashCode() and toString()

So we can use our record like this:

Range range = new Range(1, 5);

int from = range.from(); // 1
int to = range.to(); // 5
String toString = range.toString(); // Range[from=1, to=5]
boolean equals = range.equals(new Range(1, 5)); // true

Note that the accessors are named from() and to() instead of getFrom() and getTo().


What about constructors?

Assume we want to add a constructor to our Record to perform some validation:

record Range(int from, int to) {
    public Range(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException();
        }
        this.from = from;
        this.to = to;
    }
}

This will avoid that we can create invalid Range instances.
However, it is a bit annoying that we have write down the from and to fields multiple times
to perform a simple validation.

To avoid this, we can use a special form of constructors for records, called compact constructors.
This allows us to skip defining constructor parameters and assigning constructor parameters to fields.
It looks like this:

record Range(int from, int to) {
    public Range {
        if (from > to) {
            throw new IllegalArgumentException();
        }
    }
}

The result works exactly the same as the previous constructor.

custom methods
We can also add new methods and override existing methods in records.
For example:

record Range(int from, int to) {

    public int getDistance() {
        return to - from;
    }

    @Override
    public String toString() {
        return String.format("Range[from: %s, to: %s, distance: %s]",
                from, to, getDistance());
    }
}

Why are records useful?

Records simply reduce the amount of code we have to write, when you need a simple class to pass data around.
Example use cases are multiple return values from a method, compound map keys or data transfer objects.
https://en.wikipedia.org/wiki/Data_transfer_object

Assume you want to find the minimum and maximum value in a collection
With a record you can create a return type for two values with just one line:

record MinMax(int min, int max) {}

static MinMax minMax(Collection<Integer> numbers) { ... }

(Yes, you can use separate methods to find the minimum and maximum values. However, then you have to iterate the collection twice.)

Records also provide an easy way to create compound Map keys:

record NameAndDayOfBirth(String name, LocalDate dob) {}

private Map<NameAndDayOfBirth, Person> entries = ...;


summary

Records provide a less verbose way to create simple data holders.
Common use cases are multiple return values, compound map keys or data transfer objects.
For more background on records I recommend this writing by brian goetz
https://cr.openjdk.java.net/~briangoetz/amber/datum.html
You can find the example code on GitHub.