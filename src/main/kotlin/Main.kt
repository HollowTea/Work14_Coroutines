import kotlinx.coroutines.*

fun main() {

    runBlocking {

        var calculation1: String? = null
        var calculation2: String? = null
        var calculation3: String? = null

        val job1 = launch {
            try {
                withTimeout(500) {
                    calculation1 = "First number is = ${Fibonacci.take(200)}"
                }
            } catch (e: TimeoutCancellationException) {
                calculation1 = "Cant calculate first number - calculation time exceeded"
            }
        }

        val job2 = launch {
            try {
                withTimeout(2000) {
                    calculation2 = "Second number is = ${Fibonacci.take(35000)}"
                }
            } catch (e: TimeoutCancellationException) {
                calculation2 = "Cant calculate second number - calculation time exceeded"
            }
        }

        val job3 = launch {
            try {
                withTimeout(5000) {
                    calculation3 = "Third number is = ${Fibonacci.take(30000000)}"
                }
            } catch (e: TimeoutCancellationException) {
                calculation3 = "Cant calculate third number - calculation time exceeded"
            }
        }

        launch {
            var dot = 0
            println("Calculating:")
            while (job1.isActive || job2.isActive || job3.isActive) {
                if (dot < 70) {
                    print(".")
                    dot++
                    delay(10)
                } else {
                    println(".")
                    dot = 0
                }
            }
        }

        job1.join()
        job2.join()
        job3.join()
        println(" \n$calculation1 \n$calculation2 \n$calculation3")
    }
}