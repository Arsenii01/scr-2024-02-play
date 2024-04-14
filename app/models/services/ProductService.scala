package models.services

import models.Product

import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success, Try}


object ProductService {
  val products: ArrayBuffer[models.Product] = ArrayBuffer()

  // Не совсем понятно из задания, где используется ProductItem,
  // ведь эндпоинты у нас только для Product, как я понял
  // Также непонятно, по какому принципу формируется связь One-to-Many между Product и ProductItem
  // и как они будут храниться в БД, если у ProductItem только внешний ключ id на Product
//  val productItems: ArrayBuffer[ProductItem] = ArrayBuffer()


  def getAll: ArrayBuffer[models.Product] = products

  def addProduct(product: models.Product): Try[models.Product] = {
    if (products.exists(_.id == product.id)) {
      Failure(new Throwable("Product with this id already exists"))
    } else {
      products += product
      Success(product)
    }
  }

  def editProduct(product: models.Product): Try[models.Product] = {
    if (products.exists(_.id == product.id)) {
      products -= products.find(_.id == product.id).get
      products += product
      Success(product)
    } else {
      Failure(new Throwable("Product with this id doesn't exist"))
    }
  }

  def deleteProduct(id: String): Try[String] = {
    if (products.exists(_.id == id)) {
      products -= products.find(_.id == id).get
      Success("Product deleted")
    } else {
      Failure(new Throwable("Product with this id doesn't exist"))
    }
  }

  def getAllByTitle(title: String): ArrayBuffer[models.Product] = {
    products.filter(_.title == title)
  }

}
