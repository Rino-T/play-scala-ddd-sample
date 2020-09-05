package gateways.db

import models.UserEntity
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

trait DBTableDefinitions {
  protected val profile: JdbcProfile
  import profile.api._

  class UserTable(tag: Tag) extends Table[UserEntity](tag, "user") {
    val id = column[String]("id", O.PrimaryKey)
    val name = column[String]("name")
    val roleId = column[Int]("role")
    def * : ProvenShape[UserEntity] = (id, name, roleId) <> (UserEntity.tupled, UserEntity.unapply)
  }

  val userTable = TableQuery[UserTable]
}
