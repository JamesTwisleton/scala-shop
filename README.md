# scala-shop
A shop system designed in Scala

## Requirements
* [Java](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* [Scala](https://www.scala-lang.org/download/)
* [sbt](https://www.scala-sbt.org/download.html)

## Usage

`sbt "run ${item} ${item}..."`

```
$ sbt "run apple apple orange orange orange"

Thank you for shopping at Twisleton Stores!

Purchases:
-------------------------------------
Apple                           £0.60
-------------------------------------
Apple                           £0.60
-------------------------------------
Orange                          £0.25
-------------------------------------
Orange                          £0.25
-------------------------------------
Orange                          £0.25
-------------------------------------

Offers:
-------------------------------------
BOGOF on Apples
-------------------------------------
Three for the price of two on Oranges
-------------------------------------

-------------------------------------
Subtotal:                       £1.95
-------------------------------------
Discounts:                      £0.85
-------------------------------------
Total:                          £1.10
-------------------------------------

```
