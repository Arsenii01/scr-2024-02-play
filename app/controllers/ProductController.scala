package controllers

import models.Product
import models.dto.ProductDto
import models.services.ProductService
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success}

object ProductController extends Controller {

  def getAll(title: String) = Action {
    if (title.isEmpty) {
      val products = ProductService.getAll
      Ok(Json.toJson(products.map(convertToProductDto)))
    } else {
      val products = ProductService.getAllByTitle(title)
      Ok(Json.toJson(products.map(convertToProductDto)))
    }

  }

  def addProduct = Action(parse.json[Product]) { request =>
    val product = request.body
    ProductService.addProduct(product) match {
      case Success(product) => Ok(Json.toJson(product))
      case Failure(exception) => BadRequest(exception.getMessage)
    }
  }

  def editProduct = Action(parse.json[Product]) { request =>
    val product = request.body
    ProductService.editProduct(product) match {
      case Success(product) => Ok(Json.toJson(product))
      case Failure(exception) => BadRequest(exception.getMessage)
    }
  }

  def deleteProduct(id: String) = Action {
    ProductService.deleteProduct(id) match {
      case Success(product) => Ok(Json.toJson(product))
      case Failure(exception) => BadRequest(exception.getMessage)
    }
  }

  def getAllByTitle(title: String) = Action {
    val products = ProductService.getAllByTitle(title)
    Ok(Json.toJson(products.map(convertToProductDto)))
  }

  private def convertToProductDto(product: Product): ProductDto = {
    ProductDto(product.title, product.description)
  }

}
