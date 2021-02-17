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

  val offer = new Offer(Apple, 2, "BOGOF")
  val shoppingCartOffersWillApplyTo = new ShoppingCart(
    Array("Apple", "Apple", "Orange", "Orange", "Orange")
  )
  test("Offer BuyableItem set correctly") {
    assert(offer.buyableItem === Apple)
  }
  test("Offer amount set correctly") {
    assert(offer.discountAmount === 2)
  }
  test("Offer name set correctly") {
    assert(offer.name === "BOGOF")
  }

  test("BOGOF on Apples offer returns correct discount") {
    val discountAmount = offer.getDiscount(shoppingCartOffersWillApplyTo)
    assert(discountAmount === 0.6)
  }

}
