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

  class Offer(
      val buyableItem: BuyableItem,
      val discountAmount: Integer,
      val name: String
  ) {
    def getDiscount(shoppingCart: ShoppingCart): BigDecimal = {
      val buyableItemCount = shoppingCart.items
        .groupBy(identity)
        .mapValues(_.size)
        .getOrElse(buyableItem, 0)
      if (buyableItemCount < discountAmount)
        0
      else if (buyableItemCount % discountAmount == 0)
        (buyableItemCount / discountAmount) * buyableItem.price
      else
        (buyableItemCount / discountAmount).floor * buyableItem.price
    }
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

  class Checkout(val shoppingCart: ShoppingCart, val offers: Array[Offer]) {
    def subtotal = shoppingCart.totalPrice
    def total = offers.foldLeft(subtotal)((acc, currentOffer) => {
      acc - currentOffer.getDiscount(shoppingCart)
    })
  }
}
