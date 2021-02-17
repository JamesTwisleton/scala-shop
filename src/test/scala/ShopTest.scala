import Shop._
class ShopTest extends org.scalatest.funsuite.AnyFunSuite {
  test("Apple case object has name \"Apple\"") {
    assert(Apple.name === "Apple")
  }
  test("Apple case object has price 0.6") {
    assert(Apple.price === 0.6)
  }

  test("Orange case object has name \"Orange\"") {
    assert(Orange.name === "Orange")
  }
  test("Orange has price 0.25") {
    assert(Orange.price === 0.25)
  }

  test("Shopping cart items initialize properly given correct String input") {
    val shoppingCart: ShoppingCart = new ShoppingCart(Array("Apple", "Orange"))
    assert(shoppingCart.items === Array(Apple, Orange))
  }
  test(
    "Shopping cart initialization throws exception given incorrect String input"
  ) {
    assertThrows[Exception] {
      val shoppingCart: ShoppingCart = new ShoppingCart(Array("Bad", "Apple"))
    }
  }
}
