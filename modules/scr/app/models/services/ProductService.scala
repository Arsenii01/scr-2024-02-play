package models.services

import com.google.inject.Inject
import models.dao.entities.ProductItem
import models.dao.repositories.{ProductItemRepository, ProductRepository, ProductRepositoryImpl}
import models.dao.entities.Product

class ProductService @Inject()(val productRepository: ProductRepository, val productItemRepository: ProductItemRepository) {

  def listProducts(): List[Product] = productRepository.list()

  def findProductById(id: String): Option[Product] = productRepository.findById(id)

  def listProductItems(): List[ProductItem] = productItemRepository.list()

  def findByTitle(title: String): List[Product] = productRepository.findByTitle(title)

  def insertProduct(product: Product): Unit = productRepository.insert(product)

  def insertProductItem(productItem: ProductItem): Unit = {
    val product = productRepository.findById(productItem.productId)
    if (product.isDefined) {
      productItemRepository.insert(productItem)
    }
  }

  def deleteProduct(id: String): Unit = productRepository.delete(id)

  def deleteProductItem(id: String): Unit = productItemRepository.delete(id)


  def updateProductItem(productItem: ProductItem): Unit = productItemRepository.update(productItem)


}