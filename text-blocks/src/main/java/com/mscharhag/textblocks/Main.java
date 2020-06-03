package com.mscharhag.textblocks;

public class Main {

    public static void json() {
        String text = """
            {
                "name": "john",
                "age": "42"
            }
            """;

        System.out.println(text);
    }

    public static void indent() {
        String text = """
                {
                    "name": "john",
                    "age": "42"
                }
                """.indent(4).formatted();

        System.out.println(text);
    }

    public static void escape() {
        String text = """
                Java text blocks start with \"""
                """;

        System.out.println(text);
    }

    public static void eol() {
        String text = """
            1
            2 \
            3 \
            4
            5
            """;
        System.out.println(text);
    }
    public static void main(String[] args) {
        json();
        indent();
        escape();
        eol();
    }
}
