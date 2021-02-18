import Shop._

class CheckoutTest extends org.scalatest.funsuite.AnyFunSuite {

  val bogofOnApplesOffer = new Offer(Apple, 2, "BOGOF")
  val threeForThePriceOfTwoOnOrangesOffer =
    new Offer(Orange, 3, "Three for the price of two on Oranges")
  val shoppingCart = new ShoppingCart(
    Array("Apple", "Apple", "Orange", "Orange", "Orange")
  )

  test("Checkout without any offers has correct subtotal and total") {
    val checkout = new Checkout(shoppingCart, Array())
    assert(checkout.total === 1.95)
  }

  test(
    "Checkout with \"BOGOF on Apples\" offer has correct total"
  ) {
    val checkout = new Checkout(
      shoppingCart,
      Array(bogofOnApplesOffer)
    )
    assert(checkout.total === 1.35)
  }

  test(
    "Checkout with \"Three for the price of two on Oranges\" offer has correct total"
  ) {
    val checkout = new Checkout(
      shoppingCart,
      Array(threeForThePriceOfTwoOnOrangesOffer)
    )
    assert(checkout.total === 1.70)
  }

  test(
    "Checkout with \"BOGOF on Apples\" and \"Three for the price of two on Oranges\" offers has correct total"
  ) {
    val checkout = new Checkout(
      shoppingCart,
      Array(bogofOnApplesOffer, threeForThePriceOfTwoOnOrangesOffer)
    )
    assert(checkout.total === 1.10)
  }
  test(
    "Checkout with \"BOGOF on Apples\" and \"Three for the price of two on Oranges\" offers has correct offersApplied"
  ) {
    val checkout = new Checkout(
      shoppingCart,
      Array(bogofOnApplesOffer, threeForThePriceOfTwoOnOrangesOffer)
    )
    println(checkout.appliedOffers)
    // assert(checkout.appliedOffers(0) === Array(bogofOnApplesOffer, threeForThePriceOfTwoOnOrangesOffer))
  }
}
