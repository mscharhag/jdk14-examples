# jdk14 switch

Text Blocks are a JDK Enhancement Proposals (JEP 355) available as preview language feature in JDK 13 and 14.
It is planned to become a permanent feature in JDK 15.
A Text Block is a String literal that span over multiple lines and avoids the need for most escape sequences.

Motivation
Embedding formats like XML, JSON or SQL in standard Java Strings become quite annoying.
For example, a simple snippet of JSON with just two keys is barely readable in Java
because of the required escaping:

String json =
        "{\n" +
            "\"name\": \"john\",\n" +
            "\"age\": 42\n" +
        "}";

Text Blocks for the rescue

Using the new text blocks feature we can rewrite the code to this:

String text = """
        {
            "name": "john",
            "age": "42"
        }
        """;
Text blocks are opened (and closed) using tripple-quotes ("""). The text begins at the next line.
After opening a text block the rest of the line needs to stay empty.
If we print this String to the console we see:

{
    "name": "john",
    "age": "42"
}

As you might have been noticed, the identation on the left side has been stripped away.
That's because a text block is processed in three steps:

- Line terminators are normalized to the LF character. This avoids problems between different platforms (like windows and unix).
- Incidental leading white spaces and all trailing whitespaces are removed.
    Incidental leading white spaces are determined by finding the common number of leading whitespaces for all lines.
- Escape sequences are interpreted. Text blocks can contain the same escape sequences as standard strings.
    Note that two new escape sequences have been added: \s for an explicit space and \<eol> as continuation indicator (more on \<eol> later).

In case we explicitly need leading whitespaces we can use the indent() method:

String text = """
        {
            "name": "john",
            "age": "42"
        }
        """.indent(4);

This adds 4 additional leading spaces to our json snippet. So it looks like this:

    {
        "name": "john",
        "age": "42"
    }

Alternatively we can remove 4 leading spaces from the closing tripple-quotes to produce the same result:

String text = """
        {
            "name": "john",
            "age": "42"
        }
    """;

The new \<eol> escape sequence

With the new \<eol> escape sequence we can split the content of a single line into multiple lines
for better readability without creating an actual line terminator.

String text = """
        1
        2 \
        3 \
        4
        5
        """;

Results in:

1
2 3 4
5

Escaping tripple-quotes

In case we need to write trippe-quotes into a text block, only the first quote need to be escaped:

String text = """
        Java text blocks start with \"""
        """;

This produces:

Java text blocks start with """


Summary
Text blocks are a nice addition to the Java programming language.
The can greatly improve the readability of embedded strings like JSON, XML or SQL by
supporting multiple lines a removing the need for double quote escaping.

Recommended further reading: https://www.infoq.com/articles/java-text-blocks/
As always you can find all the provided examples on GitHub.