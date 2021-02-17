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
}
