package ii_collections

fun example9() {
    val result = listOf(1, 2, 3, 4).fold(1, { partResult, element -> element * partResult })
    result == 24
}

// The same as
fun whatFoldDoes(): Int {
    var result = 1
    listOf(1, 2, 3, 4).forEach { element -> result = element * result}
    return result
}

fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
    // Return the set of products ordered by every customer
    val allOrderedProducts = customers.flatMap({it.orderedProducts}).toSet()
    return customers.fold(allOrderedProducts, {
        orderedByAll, customer ->
        val orderedProducts = customer.orders.flatMap { it.products }.toSet()
        orderedByAll.intersect(orderedProducts) // [X] intersect [X;C;V] = [X] == retainAll
    })
}
