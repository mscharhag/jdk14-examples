package com.mscharhag.newswitch;

public class Main {

    private enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    private static void oldSwitch() {
        DayOfWeek day = DayOfWeek.SUNDAY;
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

        System.out.println(expectedWorkingTime);
    }

    private static void newSwitch() {
        boolean isFullTimeEmployee = false;
        DayOfWeek day = DayOfWeek.MONDAY;

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

        System.out.println(expectedWorkingTime);
    }

    public static void main(String[] args) {
        oldSwitch();
        newSwitch();
    }
}
