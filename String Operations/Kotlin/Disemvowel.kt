/*
* Disemvowel Trolls - https://www.codewars.com/kata/52fba66badcd10859f00097e/train/kotlin
Trolls are attacking your comment section!
A common way to deal with this situation is to remove all of the vowels from the trolls' comments, neutralizing the threat.
Your task is to write a function that takes a string and return a new string with all vowels removed.
For example, the string "This website is for losers LOL!" would become "Ths wbst s fr lsrs LL!".
Note: for this kata y isn't considered a vowel.
*/

fun main() {
	println(disemvowel("Harsh"))
}

fun disemvowel(str: String): String {
	val vowels = arrayOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
	return str.filterNot { vowels.contains(it) }
}

fun disemvowel(str: String): String {
	return str.replace(Regex("[AaEeIiOoUu]"), "")
}

fun disemvowel(str: String): String {
	val vowels = listOf("a", "e", "i", "o", "u")
	return str.filter { vowels.contains(it.lowercase()).not() }
}

fun disemvowel(str: String): String {
	val sb = StringBuilder()
	val vowels = listOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
	for (i in str) {
		if (!vowels.contains(i)) {
			sb.append(i)
		}
	}
	return sb.toString()
}

fun disemvowel(str: String): String = str.filterNot { it in "aiueo" || it in "AIUEO" }

fun disemvowel(str: String): String = str.replace("(?i)[aeiou]".toRegex(), "")

fun disemvowel(str: String) = str.replace("""[euioaEUIOA]""".toRegex(), "")

fun disemvowel(str: String): String {
	val vowels = listOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
	return str.filter { it !in vowels }
}

fun disemvowel(str: String): String {
	var newStr = ""
	for (char in str) {
		val lchar = char.lowercaseChar()
		if (lchar != 'a' && lchar != 'e' && lchar != 'i' && lchar != 'o' && lchar != 'u') {
			newStr += char
		}
	}
	return newStr;
}

fun disemvowel(str: String): String {
	val vowelSet = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
	return str.toCharArray().filter { !vowelSet.contains(it) }.joinToString("");
}

fun disemvowel(str: String): String {
	var str = str
	var array = arrayOf('a', 'e', 'i', 'o', 'u')
	for (elem in str) if (elem.toLowerCase() in array) str = str.replace(elem.toString(), "")
	return str
}