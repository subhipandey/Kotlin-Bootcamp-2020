package customfunctional.start

fun printResults(title: String, vararg input: Any) {
    println("----- $title -----")
    for (item in input) {
        println("\t$item")
    }
}
