import Shop._

class ReceiptGenerationTest extends org.scalatest.funsuite.AnyFunSuite {
  test(
    "Receipt is generated correctly"
  ) {
    val expectedReceipt: String =
      "\nThank you for shopping at Twisleton Stores!\n\nPurchases:\n\nApple               £0.60\nOrange              £0.25\n\nTotal:              £0.85\n"
    assert(getReceipt(Array("Apple", "Orange")) === expectedReceipt)
  }
}
