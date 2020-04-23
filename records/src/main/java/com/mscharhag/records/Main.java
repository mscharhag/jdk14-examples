package com.mscharhag.records;

public class Main {

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


    public static void main(String[] args) {
        Range range = new Range(1, 5);

        int from = range.from(); // 1
        int to = range.to(); // 5
        int distance = range.getDistance();
        String toString = range.toString(); // Range[from=1, to=5]
        boolean equals = range.equals(new Range(1, 5)); // true


        System.out.println(from);
        System.out.println(to);
        System.out.println(distance);
        System.out.println(toString);
        System.out.println(equals);
    }
}
