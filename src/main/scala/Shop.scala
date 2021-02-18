import scala.collection.mutable.ArrayBuffer

object Shop extends App {

  val shoppingCart = new ShoppingCart(args)

  val bogofOnApplesOffer =
    new Offer(Apple, 2, "BOGOF on Apples")
  val threeForThePriceOfTwoOnOrangesOffer =
    new Offer(Orange, 3, "Three for the price of two on Oranges")

  val checkout =
    new Checkout(
      shoppingCart,
      Array(bogofOnApplesOffer, threeForThePriceOfTwoOnOrangesOffer)
    )

  println(generateReceipt(checkout))

  def generateReceipt(checkout: Checkout): String = {
    var receipt =
      "\nThank you for shopping at Twisleton Stores!\n\nPurchases:\n-------------------------------------\n"
    val priceStringFormat = "%-32s%s%n-------------------------------------%n"

    checkout.shoppingCart.items.foreach(item => {
      receipt =
        receipt.concat(priceStringFormat.format(item, formatPrice(item.price)))
    })

    if (checkout.appliedOffers.length != 0) {
      receipt =
        receipt.concat("\nOffers:\n-------------------------------------\n")
      checkout.appliedOffers.foreach(offer => {
        receipt = receipt.concat(priceStringFormat.format(offer.name, ""))
      })
    }

    receipt = receipt.concat("\n-------------------------------------\n")
    if (checkout.totalDiscounts != 0) {
      receipt = receipt.concat(
        priceStringFormat.format(
          "Subtotal: ",
          formatPrice(checkout.shoppingCart.total)
        )
      )
      receipt = receipt.concat(
        priceStringFormat.format(
          "Discounts: ",
          formatPrice(checkout.totalDiscounts)
        )
      )
    }

    receipt.concat(
      priceStringFormat.format(
        "Total: ",
        formatPrice(checkout.total)
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
    val total: BigDecimal = items.map(_.price).sum
  }

  class Checkout(val shoppingCart: ShoppingCart, val offers: Array[Offer]) {
    val appliedOffers = ArrayBuffer[Offer]()
    var totalDiscounts: BigDecimal = 0
    val total = offers.foldLeft(shoppingCart.total)((acc, currentOffer) => {
      val discount = currentOffer.getDiscount(shoppingCart)
      totalDiscounts += discount
      if (discount != 0)
        appliedOffers += currentOffer
      acc - discount
    })
  }
}
