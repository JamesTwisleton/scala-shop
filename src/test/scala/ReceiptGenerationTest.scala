import Shop._

class ReceiptGenerationTest extends org.scalatest.funsuite.AnyFunSuite {
  test(
    "Receipt with no offers is generated correctly"
  ) {
    val shoppingCart = new ShoppingCart(Array("apple", "orange"))
    val checkout = new Checkout(
      shoppingCart,
      Array()
    )
    val expectedReceipt: String =
      "\nThank you for shopping at Twisleton Stores!\n\n" +
        "Purchases:\n\n" +
        "Apple               £0.60\n" +
        "Orange              £0.25\n\n" +
        "Subtotal:           £0.85\n" +
        "Total:              £0.85\n"
    assert(generateReceipt(checkout) === expectedReceipt)
  }
}
