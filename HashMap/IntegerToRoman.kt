/*
* Integer to Roman

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.

Example 1:
Input: num = 3
Output: "III"
Explanation: 3 is represented as 3 ones.

Example 2:
Input: num = 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.

Example 3:
Input: num = 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

Constraints: 1 <= num <= 3999
*/

fun main() {
	print(intToRoman(58))
}

fun intToRoman(num: Int): String {
	val ones = listOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
	val tens = listOf("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
	val hundreds = listOf("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
	val thousands = listOf("", "M", "MM", "MMM")

	return thousands[num / 1000] +
			hundreds[(num.mod(1000) / 100)] +
			tens[(num.mod(100) / 10)] +
			ones[(num.mod(10))]
}

class Solution {
	fun intToRoman(num: Int): String {
		val arr = arrayOf(
			1000 to "M",
			900 to "CM",
			500 to "D",
			400 to "CD",
			100 to "C",
			90 to "XC",
			50 to "L",
			40 to "XL",
			10 to "X",
			9 to "IX",
			5 to "V",
			4 to "IV",
			1 to "I"
		)

		var residue = num
		val ans = StringBuilder()
		for ((i, s) in arr) {
			val n = residue / i
			repeat(n) { ans.append(s) }
			residue -= (n * i)
		}

		return ans.toString()
	}
}

class Solution {
	data class RomanChar(val ch: Char, val value: Int, val before: List<RomanChar>) {
		companion object {
			val M = RomanChar('M', 1000, listOf())
			val D = RomanChar('D', 500, listOf())
			val L = RomanChar('L', 50, listOf())
			val V = RomanChar('V', 5, listOf())
			val C = RomanChar('C', 100, listOf(M, D))
			val X = RomanChar('X', 10, listOf(L, C))
			val I = RomanChar('I', 1, listOf(V, X))
		}
	}

	data class RomanCharDigitConverter(val big: RomanChar, val mid: RomanChar, val low: RomanChar) {
		fun digitToRoman(digit: Int): String {
			return when (digit) {
				9 -> low.ch.toString() + big.ch.toString()
				8 -> mid.ch.toString() + low.ch.toString() + low.ch.toString() + low.ch.toString()
				7 -> mid.ch.toString() + low.ch.toString() + low.ch.toString()
				6 -> mid.ch.toString() + low.ch.toString()
				5 -> mid.ch.toString()
				4 -> low.ch.toString() + mid.ch.toString()
				3 -> low.ch.toString() + low.ch.toString() + low.ch.toString()
				2 -> low.ch.toString() + low.ch.toString()
				1 -> low.ch.toString()
				else -> ""
			}
		}
	}

	fun intToRoman(num: Int): String {
		val hundConverter = RomanCharDigitConverter(RomanChar.M, RomanChar.D, RomanChar.C)
		val tenConverter = RomanCharDigitConverter(RomanChar.C, RomanChar.L, RomanChar.X)
		val oneConverter = RomanCharDigitConverter(RomanChar.X, RomanChar.V, RomanChar.I)
		val th = num / 1000
		var rem = num % 1000
		val hund = rem / 100
		rem %= 100
		val ten = rem / 10
		rem %= 10
		val one = rem

		val strBuilder = StringBuilder()
		for (i in 0 until th)
			strBuilder.append(RomanChar.M.ch)

		strBuilder.append(hundConverter.digitToRoman(hund))
		strBuilder.append(tenConverter.digitToRoman(ten))
		strBuilder.append(oneConverter.digitToRoman(one))

		return strBuilder.toString()
	}
}

class Solution {

	fun intToRoman(num: Int): String {

		fun Int.lessThanTen(): String {
			return when (this) {
				0 -> ""
				1 -> "I"
				2 -> "II"
				3 -> "III"
				4 -> "IV"
				5 -> "V"
				6 -> "VI"
				7 -> "VII"
				8 -> "VIII"
				9 -> "IX"
				else -> error("")
			}
		}

		fun Int.lessThanHundred(): String {
			return when (this) {
				0 -> ""
				10 -> "X"
				20 -> "XX"
				30 -> "XXX"
				40 -> "XL"
				50 -> "L"
				60 -> "LX"
				70 -> "LXX"
				80 -> "LXXX"
				90 -> "XC"
				else -> error("")
			}
		}

		fun Int.lessThanThousand(): String {
			return when (this) {
				0 -> ""
				100 -> "C"
				200 -> "CC"
				300 -> "CCC"
				400 -> "CD"
				500 -> "D"
				600 -> "DC"
				700 -> "DCC"
				800 -> "DCCC"
				900 -> "CM"
				else -> error("")
			}
		}

		fun Int.lessThanTenThousand(): String {
			return when (this) {
				0 -> ""
				1000 -> "M"
				2000 -> "MM"
				3000 -> "MMM"
				else -> error("")
			}
		}

		val thousands = (num / 1000) * 1000
		val hundreds = ((num % 1000) / 100) * 100
		val tens = ((num % 100) / 10) * 10
		val ones = (num % 10)

		return buildString {
			append(thousands.lessThanTenThousand())
			append(hundreds.lessThanThousand())
			append(tens.lessThanHundred())
			append(ones.lessThanTen())
		}
	}
}

class Solution {
	fun intToRoman(num: Int): String {
		val values = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
		val symbols = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")

		val ans = StringBuilder()
		var numCopy = num

		for (idx in values.indices) {
			while (values[idx] <= numCopy) {
				numCopy -= values[idx]
				ans.append(symbols[idx])
			}
		}

		return ans.toString()

	}
}

class Solution {
	fun intToRoman(num: Int): String {
		var cloneNum = num
		var result = ""

		while (cloneNum >= 1000) {
			result += 'M'
			cloneNum -= 1000
		}

		while (cloneNum >= 100) {
			if (cloneNum >= 900) {
				result += "CM"
				cloneNum -= 900
			} else if (cloneNum >= 500) {
				result += 'D'
				cloneNum -= 500
			} else if (cloneNum >= 400) {
				result += "CD"
				cloneNum -= 400
			} else {
				result += "C"
				cloneNum -= 100
			}
		}
		while (cloneNum >= 10) {
			if (cloneNum >= 90) {
				result += "XC"
				cloneNum -= 90
			} else if (cloneNum >= 50) {
				result += 'L'
				cloneNum -= 50
			} else if (cloneNum >= 40) {
				result += "XL"
				cloneNum -= 40
			} else {
				result += "X"
				cloneNum -= 10
			}
		}
		while (cloneNum > 0) {
			if (cloneNum >= 9) {
				result += "IX"
				cloneNum -= 9
			} else if (cloneNum >= 5) {
				result += 'V'
				cloneNum -= 5
			} else if (cloneNum == 4) {
				result += "IV"
				break
			} else {
				result += "I"
				cloneNum -= 1
			}
		}

		return result
	}
}

class Solution {

	val numerals = listOf('I', 'V', 'X', 'L', 'C', 'D', 'M')

	fun intToRoman(num: Int): String {

		return num.toString().reversed().toCharArray().mapIndexed { i, char: Char ->
			when (val digit = char.toString().toInt()) {
				in 0..3 -> numerals[i * 2].times(digit)
				4 -> numerals[i * 2].times(1) + numerals[i * 2 + 1].times(1)
				in 5..8 -> numerals[i * 2 + 1].times(1) + numerals[i * 2].times(digit - 5)
				9 -> numerals[i * 2].times(1) + numerals[i * 2 + 2].times(1)
				else -> error("unreachable $char")
			}
		}.reversed().joinToString(separator = "")

	}
}

fun Char.times(amount: Int) = kotlin.text.StringBuilder(amount).apply {
	for (i in 1..amount) append(this@times)
}.toString()

class Solution {
	fun intToRoman(num: Int): String {
		val sb = StringBuilder()
		var num = num

		while (num >= 1000) {
			sb.append("M")
			num -= 1000
		}
		while (num >= 900) {
			sb.append("CM")
			num -= 900
		}
		while (num >= 500) {
			sb.append("D")
			num -= 500
		}
		while (num >= 400) {
			sb.append("CD")
			num -= 400
		}
		while (num >= 100) {
			sb.append("C")
			num -= 100
		}
		while (num >= 90) {
			sb.append("XC")
			num -= 90
		}
		while (num >= 50) {
			sb.append("L")
			num -= 50
		}
		while (num >= 40) {
			sb.append("XL")
			num -= 40
		}
		while (num >= 10) {
			sb.append("X")
			num -= 10
		}
		while (num >= 9) {
			sb.append("IX")
			num -= 9
		}
		while (num >= 5) {
			sb.append("V")
			num -= 5
		}
		while (num >= 4) {
			sb.append("IV")
			num -= 4
		}
		while (num >= 1) {
			sb.append("I")
			num -= 1
		}
		return sb.toString()
	}
}

class Solution {
	fun intToRoman(num: Int): String {
		var remainingNumber = num
		var resultString = ""
		val remainingSymbols = RomanSymbol.values().toMutableList()

		while (remainingNumber > 0) {
			val symbol = remainingSymbols.first()
			if (remainingNumber / symbol.intValue > 0) {
				resultString = "$resultString${symbol.string}"
				remainingNumber -= symbol.intValue
			} else {
				remainingSymbols.removeFirst()
			}
		}

		return resultString
	}

	enum class RomanSymbol(val intValue: Int, val string: String) {
		M(1000, "M"),
		CM(900, "CM"),
		D(500, "D"),
		CD(400, "CD"),
		C(100, "C"),
		XC(90, "XC"),
		L(50, "L"),
		XL(40, "XL"),
		X(10, "X"),
		IX(9, "IX"),
		V(5, "V"),
		IV(4, "IV"),
		III(3, "III"),
		II(2, "II"),
		I(1, "I"),
	}
}

class Solution {
	fun intToRoman(num: Int): String {
		builder.setLength(0)
		var n = num
		var i = 0

		while (i < pairs.size) {
			val pair = pairs[i]
			while (n >= pair.first) {
				n -= pair.first
				builder.append(pair.second)
			}
			i++
		}
		return builder.toString()
	}

	companion object {
		val pairs = arrayOf(1000 to "M", 900 to "CM", 500 to "D", 400 to "CD", 100 to "C", 90 to "XC", 50 to "L", 40 to "XL", 10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I")
		val builder = StringBuilder()
	}
}