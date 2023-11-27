/*
* Given a time in -hour AM/PM format, convert it to military (24-hour) time.

Note: - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock.
- 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.

Example
s="12:01:00PM"
Return '12:01:00'.

s="12:01:00AM"
Return '00:01:00'.

Function Description
Complete the timeConversion function in the editor below. It should return a new string representing the input time in 24 hour format.
timeConversion has the following parameter(s):
string s: a time in  hour format

Returns
string: the time in  hour format

Input Format
A single string  that represents a time in -hour clock format (i.e.:  or ).

Constraints

All input times are valid
Sample Input
07:05:45PM

Sample Output
19:05:45

 * Complete the 'timeConversion' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING s as parameter.
 */

fun timeConversion(s: String): String {
    // Write your code here
    val colon = s.indexOf(":")
    val hour = s.substring(0, colon)
    val min = s.substring(colon + 1, s.length - 2)
    val amOrPm: String = s.substring(s.length - 2)
    var hourInt = hour.toInt()

    when {
        (hourInt < 12) && (amOrPm != "AM") -> {
            hourInt += 12
        }

        hourInt == 12 -> {
            hourInt = if (amOrPm == "PM") 12 else 0
        }
    }

    if (hourInt == 0 || amOrPm == "AM")
        return "0$hourInt:$min"
    return "$hourInt:$min"
}

fun main(args: Array<String>) {
    val s = readln()!!
    val result = timeConversion(s)
    println(result)
}
