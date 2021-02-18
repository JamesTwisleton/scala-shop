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
        "Purchases:\n" +
        "-------------------------------------\n" +
        "Apple                           £0.60\n" +
        "-------------------------------------\n" +
        "Orange                          £0.25\n" +
        "-------------------------------------\n\n" +
        "-------------------------------------\n" +
        "Total:                          £0.85\n" +
        "-------------------------------------\n"
    assert(generateReceipt(checkout) === expectedReceipt)
  }

  test(
    "Receipt with  \"BOGOF on Apples\" offer is generated correctly"
  ) {
    val shoppingCart =
      new ShoppingCart(Array("apple", "apple", "orange", "orange", "orange"))
    val bogofOnApplesOffer = new Offer(Apple, 2, "BOGOF on Apples")

    val checkout = new Checkout(
      shoppingCart,
      Array(bogofOnApplesOffer)
    )
    val expectedReceipt: String =
      "\nThank you for shopping at Twisleton Stores!\n\n" +
        "Purchases:\n" +
        "-------------------------------------\n" +
        "Apple                           £0.60\n" +
        "-------------------------------------\n" +
        "Apple                           £0.60\n" +
        "-------------------------------------\n" +
        "Orange                          £0.25\n" +
        "-------------------------------------\n" +
        "Orange                          £0.25\n" +
        "-------------------------------------\n" +
        "Orange                          £0.25\n" +
        "-------------------------------------\n\n" +
        "Offers:\n" +
        "-------------------------------------\n" +
        "BOGOF on Apples                 \n" +
        "-------------------------------------\n\n" +
        "-------------------------------------\n" +
        "Subtotal:                       £1.95\n" +
        "-------------------------------------\n" +
        "Discounts:                      £0.60\n" +
        "-------------------------------------\n" +
        "Total:                          £1.35\n" +
        "-------------------------------------\n"

    assert(generateReceipt(checkout) === expectedReceipt)
  }

  test(
    "Receipt with  \"BOGOF on Apples\" and \"Three for the price of two on Oranges\" offers is generated correctly"
  ) {
    val shoppingCart =
      new ShoppingCart(Array("apple", "apple", "orange", "orange", "orange"))
    val bogofOnApplesOffer = new Offer(Apple, 2, "BOGOF on Apples")
    val threeForThePriceOfTwoOnOrangesOffer =
      new Offer(Orange, 3, "Three for the price of two on Oranges")

    val checkout = new Checkout(
      shoppingCart,
      Array(bogofOnApplesOffer, threeForThePriceOfTwoOnOrangesOffer)
    )
    val expectedReceipt: String =
      "\nThank you for shopping at Twisleton Stores!\n\n" +
        "Purchases:\n" +
        "-------------------------------------\n" +
        "Apple                           £0.60\n" +
        "-------------------------------------\n" +
        "Apple                           £0.60\n" +
        "-------------------------------------\n" +
        "Orange                          £0.25\n" +
        "-------------------------------------\n" +
        "Orange                          £0.25\n" +
        "-------------------------------------\n" +
        "Orange                          £0.25\n" +
        "-------------------------------------\n\n" +
        "Offers:\n" +
        "-------------------------------------\n" +
        "BOGOF on Apples                 \n" +
        "-------------------------------------\n" +
        "Three for the price of two on Oranges\n" +
        "-------------------------------------\n\n" +
        "-------------------------------------\n" +
        "Subtotal:                       £1.95\n" +
        "-------------------------------------\n" +
        "Discounts:                      £0.85\n" +
        "-------------------------------------\n" +
        "Total:                          £1.10\n" +
        "-------------------------------------\n"

    assert(generateReceipt(checkout) === expectedReceipt)
  }
}
