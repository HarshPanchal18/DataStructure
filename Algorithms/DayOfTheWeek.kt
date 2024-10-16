import java.util.*

/*
* Day of the Week

Given a date, return the corresponding day of the week for that date.
The input is given as three integers representing the day, month and year respectively.
Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.

Example 1:
Input: day = 31, month = 8, year = 2019
Output: "Saturday"

Example 2:
Input: day = 18, month = 7, year = 1999
Output: "Sunday"

Example 3:
Input: day = 15, month = 8, year = 1993
Output: "Sunday"

Constraints:
The given dates are valid dates between the years 1971 and 2100.
*/

fun main() {
    println(dayOfTheWeek(31, 8, 2019))
}

fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
    val days = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, day)
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    return days[dayOfWeek - 1]
}

class Solution {
    val days = listOf("Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday")

    // We don't need December's day count since this solution only needs the day counts of months before the param month.
    val monthDayCounts = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30)

    fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
        var daysPassed: Int = ((year - 1971) * 365) + monthDayCounts.take(month - 1).sum() + (day - 1)

        // Find the number of leap days from past leap years.
        if (year >= 1973)
            daysPassed += (year - 1973) / 4 + 1

        // See if the year is a leap year and if leap day has been passed.
        if (year % 4 == 0 && year != 2100 && month > 2) daysPassed++

        return days[daysPassed % 7]
    }
}

class Solution {
    fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
        return arrayOf(
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
        )[Calendar.getInstance()
            .apply {
                set(year, month - 1, day)
            }.get(Calendar.DAY_OF_WEEK) - 1]
    }
}

class Solution {
    fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
        val weeks = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        val months = intArrayOf(0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5)
        val leapMonths = intArrayOf(6, 2, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5)

        val century = century(year)

        val years = year % 100
        val adjust = (years / 4)

        val month1 = if (leap(year)) leapMonths[month - 1] else months[month - 1]

        val finalDays = years + adjust + month1 + century + day
        return weeks[finalDays % 7]
    }

    fun century(year: Int): Int {
        val century = year / 100
        return when (century) {
            19 -> 0
            20 -> 6
            21 -> 4
            else -> 0
        }
    }

    fun leap(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}

class Solution {
    fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
        val weekDays = arrayOf("Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday")
        val x = (dayToYear(year) + dayOfYear(day, month, year)) % 7

        return weekDays[x]
    }

    fun dayToYear(year: Int): Int {
        var result = year - 1971
        result += (year - 1968 - 1) / 4
        return result
    }

    fun dayOfYear(day: Int, month: Int, year: Int): Int {
        var result = 0
        var y = 0

        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) y = 1

        when (month) {
            1 -> result = day
            2 -> result = 31 + day
            3 -> result = 59 + day + y
            4 -> result = 90 + day + y
            5 -> result = 120 + day + y
            6 -> result = 151 + day + y
            7 -> result = 181 + day + y
            8 -> result = 212 + day + y
            9 -> result = 243 + day + y
            10 -> result = 273 + day + y
            11 -> result = 304 + day + y
            12 -> result = 334 + day + y
        }
        return result
    }
}

class Solution {
    fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
        val calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day)
        val day = calendar.get(Calendar.DAY_OF_WEEK);

        when (day) {
            Calendar.SUNDAY -> return "Sunday"
            Calendar.MONDAY -> return "Monday"
            Calendar.TUESDAY -> return "Tuesday"
            Calendar.WEDNESDAY -> return "Wednesday"
            Calendar.THURSDAY -> return "Thursday"
            Calendar.FRIDAY -> return "Friday"
            Calendar.SATURDAY -> return "Saturday"
            else -> return "-1"
        }
    }
}

class Solution {
    val daysIn4Years = 365*4+1
    val daysInMonths = listOf(31,28,31,30,31,30,31,31,30,31,30,31)

    val dayOfTheWeek = listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    fun daysSince1900(day: Int, month: Int, year: Int):Int {
        var result = 0
        result += (year-1901) / 4 * daysIn4Years + (year-1901) % 4 * 365
        var isLeapYear = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)

        for (i in 0..month-2)
            result += daysInMonths[i]

        if (isLeapYear && month>2) result++

        result+=day

        return result
    }


    fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
        return dayOfTheWeek[(daysSince1900(day,month,year)+1)%7]
    }
}

import java.time.LocalDate
import java.time.DayOfWeek

class Solution {
    fun dayOfTheWeek(day: Int, month: Int, year: Int): String {
        var datetime = LocalDate.of(year, month, day)
        var outputMap = mapOf(
            "SUNDAY" to "Sunday",
            "MONDAY" to "Monday",
            "TUESDAY" to "Tuesday",
            "WEDNESDAY" to "Wednesday",
            "THURSDAY" to "Thursday",
            "FRIDAY" to "Friday",
            "SATURDAY" to "Saturday"
        )
        var dayOfWeek = datetime.dayOfWeek.toString()
        return(outputMap.getOrDefault(dayOfWeek, "N/A"))
    }
}