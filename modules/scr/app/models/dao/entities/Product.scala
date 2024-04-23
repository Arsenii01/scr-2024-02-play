package models.dao.entities

import org.squeryl.KeyedEntity
import play.api.libs.json.{Json, Reads, Writes}

case class Product(id: String, title: String, description: String)

object Product {
  implicit val productWrites: Writes[Product] = Json.writes[Product]
  implicit val productReads: Reads[Product] = Json.reads[Product]
}

case class ProductItem(id: String, price: Int, quantity: Int, productId: String) extends KeyedEntity[String]

object ProductItem {
  implicit val productItemWrites: Writes[ProductItem] = Json.writes[ProductItem]
  implicit val productItemReads: Reads[ProductItem] = Json.reads[ProductItem]
}