package gateways.user

import com.rinotc.domain.model.user.{DatabaseError, User, UserId, UserNotFound, UserRepository, UserRepositoryError}
import gateways.db.DaoSlick
import javax.inject.Inject
import models.{UserEntity, UserRoleIdUndefined}
import play.api.Logger
import play.api.db.slick.DatabaseConfigProvider

import scala.concurrent.{ExecutionContext, Future}

class UserRepositoryImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                                  (implicit ec: ExecutionContext) extends UserRepository with DaoSlick {

  import profile.api._

  val userRepositoryLogger: Logger = Logger("application.userRepository")

  override def save(user: User): Future[Int] = {
    val query = userTable += UserEntity.fromUser(user)
    db.run(query)
  }

  override def remove(user: User): Future[Int] = {
    val query = userTable.filter(_.id === user.id.value).delete
    db.run(query)
  }

  override def findAll: Future[Seq[User]] = {
    val query = userTable.result.map(_.flatMap { _.toUser match {
      case Right(u) => Some(u)
      case Left(entityError) =>
        entityError match { // エラーの定義が散在している気がする。。
          case UserRoleIdUndefined(value) => userRepositoryLogger.error(s"roleId: $value is undefined")
        }
        None
    }})
    db.run(query)
  }

  override def find(id: UserId): Future[Either[UserRepositoryError, User]]= {
    val query = userTable.filter(_.id === id.value).result.headOption.map {
      case Some(user) => user.toUser match {
        case Right(u) => Right(u)
        case Left(entityError) =>
          entityError match {
            case UserRoleIdUndefined(value) => userRepositoryLogger.error(s"roleId: $value is undefined")
          }
          Left(DatabaseError)
      }
      case None => Left(UserNotFound)
    }
    db.run(query)
  }
}
