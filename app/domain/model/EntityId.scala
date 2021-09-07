package domain.model

trait EntityId[+A] {
  val value: A
}
