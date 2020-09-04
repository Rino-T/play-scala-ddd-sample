package com.rinotc.domain.model.user

case class User(id: UserId, name: UserName, role: UserRole) {
  def renameTo: UserName => User = name => copy(name = name)
}

case class UserId(value: String)

case class UserName(value: String) {
  require(value.length >= 3 && value.length <= 10)
}

sealed abstract class UserRole

case object Admin extends UserRole

case object Member extends UserRole
