import kotlinx.coroutines.*
import java.math.BigInteger

object Fibonacci {
    suspend fun take(number: Int): BigInteger {
        var n1 = 0.toBigInteger()
        var n2 = 1.toBigInteger()
        var n3 = 0.toBigInteger()
        var calculate = 0
        return when (number) {
            1 -> n1
            2 -> n2
            else -> {
                for (i in calculate until number - 2) {
                    n3 = n2 + n1
                    calculate++
                    n1 = n2
                    n2 = n3
                    yield()
                }
                n3
            }
        }
    }
}