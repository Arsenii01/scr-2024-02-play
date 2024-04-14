package models.dto

import play.api.libs.json.{Json, Writes}

case class ProductDto(title: String, description: String)

object ProductDto {
  implicit val writes: Writes[ProductDto] = Json.writes[ProductDto]
}