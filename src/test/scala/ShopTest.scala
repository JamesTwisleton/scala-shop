import Shop._
class ShopTest extends org.scalatest.funsuite.AnyFunSuite {
  test("Apple case object has name \"Apple\"") {
    assert(Apple.name === "Apple")
  }
  test("Apple has price 0.6") {
    assert(Apple.price === 0.6)
  }

  test("Orange case object has name \"Orange\"") {
    assert(Orange.name === "Orange")
  }
  test("Orange has price 0.25") {
    assert(Orange.price === 0.25)
  }

  test("Shopping cart items initialize correctly given valid String input") {
    val shoppingCart: ShoppingCart = new ShoppingCart(Array("Apple", "Orange"))
    assert(shoppingCart.items === Array(Apple, Orange))
  }
  test(
    "Shopping cart initialization throws exception given invalid String input"
  ) {
    assertThrows[Exception] {
      val shoppingCart: ShoppingCart = new ShoppingCart(Array("Bad", "Apple"))
    }
  }

  test(
    "Receipt is generated correctly"
  ) {
    val expectedReceipt: String =
      "\nThank you for shopping at Twisleton Stores!\n\nPurchases:\n\nApple               £0.60\nOrange              £0.25\n\nTotal:              £0.85\n"
    assert(getReceipt(Array("Apple", "Orange")) === expectedReceipt)
  }

  val bogofOnApplesOffer = new Offer(Apple, 2, "BOGOF")
  val threeForThePriceOfTwoOnOrangesOffer =
    new Offer(Orange, 3, "Three for the price of two on Oranges")
  val shoppingCartOffersWillApplyTo = new ShoppingCart(
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
      bogofOnApplesOffer.getDiscount(shoppingCartOffersWillApplyTo) === 0.6
    )
  }
  test(
    "\"Three for the price of two on Oranges\" offer returns correct discount"
  ) {

    assert(
      threeForThePriceOfTwoOnOrangesOffer.getDiscount(
        shoppingCartOffersWillApplyTo
      ) === 0.25
    )
  }

  test("Checkout without any offers has correct subtotal and total") {
    val checkout = new Checkout(shoppingCartOffersWillApplyTo, Array())
    assert(checkout.subtotal === 1.95)
  }

  test(
    "Checkout with \"BOGOF on Apples\" offer has correct subtotal and total"
  ) {
    val checkout = new Checkout(
      shoppingCartOffersWillApplyTo,
      Array(bogofOnApplesOffer)
    )
    assert(checkout.subtotal === 1.95)
    assert(checkout.total === 1.35)
  }

  test(
    "Checkout with \"Three for the price of two on Oranges\" offer has correct subtotal and total"
  ) {
    val checkout = new Checkout(
      shoppingCartOffersWillApplyTo,
      Array(threeForThePriceOfTwoOnOrangesOffer)
    )
    assert(checkout.subtotal === 1.95)
    assert(checkout.total === 1.70)
  }

  test(
    "Checkout with \"BOGOF on Apples\" and \"Three for the price of two on Oranges\" offers has correct subtotal and total"
  ) {
    val checkout = new Checkout(
      shoppingCartOffersWillApplyTo,
      Array(bogofOnApplesOffer, threeForThePriceOfTwoOnOrangesOffer)
    )
    assert(checkout.subtotal === 1.95)
    assert(checkout.total === 1.10)
  }

}
