/*
* Convert A Hex String To RGB

When working with color values it can sometimes be useful to extract the individual red, green, and blue (RGB) component values for a color. Implement a function that meets these requirements:
Accepts a case-insensitive hexadecimal color string as its parameter (ex. "#FF9933" or "#ff9933")
Returns a Map<String, int> with the structure {r: 255, g: 153, b: 51} where r, g, and b range from 0 through 255
Note: your implementation does not need to support the shorthand form of hexadecimal notation (ie "#FFF")

Example
"#FF9933" --> {r: 255, g: 153, b: 51}
*/

fun main() {
    val color = hexStringToRGB("#Ff9933")
    println(color.r)
    println(color.g)
    println(color.b)
}

data class RGB(val r: Int, val g: Int, val b: Int)

@OptIn(ExperimentalStdlibApi::class)
fun hexStringToRGB(hexString: String): RGB {
    val codes = hexString
        .substringAfter('#')
        .chunked(2)
        .map { it.hexToInt(HexFormat.Default) }
    return RGB(codes[0], codes[1], codes[2])
}

fun hexStringToRGB(hexString: String): RGB {
    val (red, green, blue) = hexString
        .drop(1)
        .chunked(2)
        .map { it.toInt(16) }
    return RGB(red, green, blue)
}

fun hexStringToRGB(hexString: String): RGB {
    return hexString
        .drop(1)
        .chunked(2) { "$it".toInt(16) }
        .let { RGB(it[0], it[1], it[2]) }
}

fun hexStringToRGB(hexString: String): RGB = RGB(
    Integer.valueOf(hexString.substring(1, 3), 16),
    Integer.valueOf(hexString.substring(3, 5), 16),
    Integer.valueOf(hexString.substring(5, 7), 16)
)

fun hexStringToRGB(hexString: String): RGB {
    val r = hexString.substring(1, 3).toInt(16)
    val g = hexString.substring(3, 5).toInt(16)
    val b = hexString.substring(5, 7).toInt(16)
    return RGB(r, g, b)
}

fun hexStringToRGB(hexString: String): RGB? {
    val hexColor = Integer.parseInt(hexString.substring(1), 16)
    val r = (hexColor and 0xFF0000) shr 16
    val g = (hexColor and 0xFF00) shr 8
    val b = (hexColor and 0xFF)
    return RGB(r, g, b)
}