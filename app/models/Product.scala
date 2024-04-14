package models

import play.api.libs.json.{Json, Reads, Writes}

case class Product(id: String, title: String, description: String)

object Product {
  implicit val writes: Writes[Product] = Json.writes[Product]
  implicit val reads: Reads[Product] = Json.reads[Product]
}
