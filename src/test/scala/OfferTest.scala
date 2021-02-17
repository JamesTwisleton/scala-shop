import Shop._

class OfferTest extends org.scalatest.funsuite.AnyFunSuite {

  val bogofOnApplesOffer = new Offer(Apple, 2, "BOGOF")
  val threeForThePriceOfTwoOnOrangesOffer =
    new Offer(Orange, 3, "Three for the price of two on Oranges")
  val shoppingCart = new ShoppingCart(
    Array("Apple", "Apple", "Orange", "Orange", "Orange")
  )
  test("Offer BuyableItem set correctly") {
    assert(bogofOnApplesOffer.buyableItem === Apple)
  }
  test("Offer amount set correctly") {
    assert(bogofOnApplesOffer.discountAmount === 2)
  }
  test("Offer name set correctly") {
    assert(bogofOnApplesOffer.name === "BOGOF")
  }
  test("\"BOGOF on Apples\" offer returns correct discount") {
    assert(
      bogofOnApplesOffer.getDiscount(shoppingCart) === 0.6
    )
  }
  test(
    "\"Three for the price of two on Oranges\" offer returns correct discount"
  ) {

    assert(
      threeForThePriceOfTwoOnOrangesOffer.getDiscount(
        shoppingCart
      ) === 0.25
    )
  }
}
