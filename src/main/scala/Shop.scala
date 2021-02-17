object Shop extends App {
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
