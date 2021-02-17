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
}
