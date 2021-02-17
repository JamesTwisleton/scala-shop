object Shop extends App {

  println(getReceipt(args))

  def getReceipt(items: Array[String]): String = {
    val shoppingCart: ShoppingCart = new ShoppingCart(items)
    var receipt: String =
      "\nThank you for shopping at Twisleton Stores!\n\nPurchases:\n\n"
    val priceStringFormat: String = "%-20s%s%n"

    shoppingCart.items.foreach(item => {
      receipt =
        receipt.concat(priceStringFormat.format(item, formatPrice(item.price)))
    })

    receipt.concat(
      "\n" +
        priceStringFormat.format(
          "Total: ",
          formatPrice((shoppingCart.totalPrice))
        )
    )
  }

  def formatPrice(price: BigDecimal): String = {
    "£" + price.setScale(2)
  }

  trait BuyableItem {
    def name: String
    def price: BigDecimal
  }

  case object Apple extends BuyableItem {
    val name = "Apple"
    val price = 0.6
  }

  case object Orange extends BuyableItem {
    val name = "Orange"
    val price = 0.25
  }

  class ShoppingCart(providedItems: Array[String]) {
    val items: Array[BuyableItem] = providedItems.map(
      _.toLowerCase match {
        case "apple"  => Apple
        case "orange" => Orange
        case _        => throw new Exception("Invalid item")
      }
    )
    val totalPrice: BigDecimal = items.map(_.price).sum
  }
}
