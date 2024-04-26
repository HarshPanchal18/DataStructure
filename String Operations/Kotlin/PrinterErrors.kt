/*
* Printer Errors - https://www.codewars.com/kata/56541980fa08ab47a0000040/train/kotlin

In a factory a printer prints labels for boxes. For one kind of boxes the printer has to use colors which, for the sake of simplicity, are named with letters from a to m.
The colors used by the printer are recorded in a control string. For example a "good" control string would be aaabbbbhaijjjm meaning that the printer used three times color a, four times color b, one time color h then one time color a...
Sometimes there are problems: lack of colors, technical malfunction and a "bad" control string is produced e.g. aaaxbbbbyyhwawiwjjjwwm with letters not from a to m.
You have to write a function printer_error which given a string will return the error rate of the printer as a string representing a rational whose numerator is the number of errors and the denominator the length of the control string. Don't reduce this fraction to a simpler expression.
The string has a length greater or equal to one and contains only letters from a to z.

Examples:
s="aaabbbbhaijjjm"
printer_error(s) => "0/14"

s="aaaxbbbbyyhwawiwjjjwwm"
printer_error(s) => "8/22"
*/

fun main() {
	println(printerError("aaaxbbbbyyhwawiwjjjwwm"))
}

fun printerError(s: String): String {
	// aaaxbbbbyyhwawiwjjjwwm
	val valid = s.count { it.code < 110 }
	return "${s.length - valid}/${s.length}"
}

fun printerError(s: String) = "${s.count { it !in 'a'..'m' }}/${s.length}"

fun printerError(s: String): String {
	val errors = s.count { it !in 'a'..'m' }
	val length = s.length
	return "$errors/$length"
}

fun printerError(s: String): String {
	return "${s.count { it > 'm' }}/${s.length}"
}

fun printerError(s: String): String {
	var t = 0
	for (element in s) {
		if (element in 'n'..'z') {
			t++
		}
	}
	return ("$t/${s.length}")
}

fun printerError(s: String): String {
	var inc = 0
	for (i in s) {
		if (i !in 'a'..'m') {
			inc++
		}
	}
	return "$inc/${s.count()}"
}

fun printerError(s: String): String {
	var count = 0 // count the number of letters that are not in the range of a-m
	for (i in s) {
		if (i > 'm') { // if the letter is not in the range of a-m
			count++
		}
	}
	return "$count/${s.length}"
}

fun printerError(s: String) = s.mapNotNull { if (it in 'a'..'m') null else it }.let { "${it.count()}/${s.length}" }

fun printerError(s: String): String {
	val bad = "nopqrstuvwxyz"
	var err = 0;
	s.forEach { if (bad.contains(it)) err++ }
	return "$err/${s.length}"
}