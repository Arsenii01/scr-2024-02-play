package controllers

import com.google.inject.Inject
import models.dao.entities.{Product, ProductItem}
import models.dto.ProductDto
import models.services.ProductService
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller, Request}

class ProductController @Inject()(val productService: ProductService) extends Controller {

  def listProducts(title: Option[String]) = Action {
    if (title.isDefined) {
      Ok(Json.toJson(productService.findByTitle(title.get).map(convertToProductDto)))
    } else {
      Ok(Json.toJson(productService.listProducts().map(convertToProductDto)))
    }
  }

  def listProductItems = Action {
    Ok(Json.toJson(productService.listProductItems()))
  }



  def insertProduct = Action(parse.json[Product]) { request =>
    val product = request.body
    productService.insertProduct(product)
    Ok
  }


  def insertProductItem = Action(parse.json[ProductItem]){ request =>
    val productItem = request.body
    productService.insertProductItem(productItem)
    Ok
  }

  def deleteProduct(id: String) = Action {
    productService.deleteProduct(id)
    Ok
  }

  def deleteProductItem(id: String) = Action {
    productService.deleteProductItem(id)
    Ok
  }

  def updateProductItem = Action(parse.json[ProductItem]) { request =>
    val productItem = request.body
    productService.updateProductItem(productItem)
    Ok
  }

  private def convertToProductDto(product: Product): ProductDto = {
    ProductDto(product.title, product.description)
  }


}
