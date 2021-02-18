import org.scalatest.funsuite.AnyFunSuite
import Shop._

class ShoppingCartTest extends AnyFunSuite {

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
}
