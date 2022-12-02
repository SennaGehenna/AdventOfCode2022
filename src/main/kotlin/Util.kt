import java.io.InputStream

fun getResourceAsStream(path: String): InputStream? =
    object {}.javaClass.getResourceAsStream(path)

fun InputStream?.readAsOneLine() = this?.bufferedReader()?.readText()?.trimEnd('\r', '\n') ?: ""