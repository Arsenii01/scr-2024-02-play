package models

import play.api.libs.json.{Json, Reads, Writes}

case class Book(id: BookId, title: Title, author: Author)

object Book {
  implicit val reads = Json.reads[Book]

  implicit val writes: Writes[Book] = Json.writes[Book]
}


case class BookId(raw: Int)

object BookId {
  implicit val reads: Reads[BookId] = Json.reads[BookId]
  implicit val writes: Writes[BookId] = Json.writes[BookId]

}

case class Title(raw: String)

object Title {
  implicit val reads: Reads[Title] = Json.reads[Title]
  implicit val writes: Writes[Title] = Json.writes[Title]

}

case class Author(raw: String)

object Author {
  implicit val reads: Reads[Author] = Json.reads[Author]

  implicit val writes: Writes[Author] = Json.writes[Author]

}

