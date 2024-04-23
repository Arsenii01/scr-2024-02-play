package models.dao.repositories

import models.dao.entities.{Product, ProductItem}
import models.dao.schema.ProductSchema

trait ProductRepository {

  def list(): List[Product]
  def findByTitle(title: String): List[Product]

  def findById(id:String): Option[Product]

  def insert(product: Product): Unit

//  def update(product: Product): Unit

  def delete(id: String): Unit

}

trait ProductItemRepository {

  def list(): List[ProductItem]

  def insert(productItem: ProductItem) : Unit

  def update(productItem: ProductItem) : Unit

  def delete(id: String): Unit

}

class ProductRepositoryImpl extends ProductRepository {

  val products = ProductSchema.products

  import org.squeryl.PrimitiveTypeMode._

  override def list(): List[Product] = transaction(from(products)(r => select(r)).toList)

  def findById(id:String): Option[Product] = transaction(from(products)(r => where(r.id === id) select(r) ).headOption)

  override def findByTitle(title: String): List[Product] = transaction(from(products)(r => where(r.title === title) select(r) ).toList)

  override def insert(product: Product): Unit = transaction(products.insert(product))

// override def update(product: Product): Unit = transaction(products.update(product))

  override def delete(id: String): Unit = transaction(products.deleteWhere(_.id === id))
}

class ProductItemRepositoryImpl extends ProductItemRepository {
  val productItems = ProductSchema.productItems

  import org.squeryl.PrimitiveTypeMode._

  override def list(): List[ProductItem] = transaction(from(productItems)(r => select(r)).toList)

  override def insert(productItem: ProductItem) : Unit = transaction(productItems.insert(productItem))

  override def update(productItem: ProductItem) : Unit = transaction(productItems.update(productItem))

  override def delete(id: String): Unit = transaction(productItems.deleteWhere(_.id === id))

}
