package models


case class UserEntity(id: String, name: String, role: Int) {
  require(id.nonEmpty)
  require(name.nonEmpty)
}
