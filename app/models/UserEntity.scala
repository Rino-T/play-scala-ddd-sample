package models

import com.rinotc.domain.model.user._

case class UserEntity(id: String, name: String, roleId: Int) {
  require(id.nonEmpty)
  require(name.nonEmpty)

  // 内側への依存ならば問題ないので、ゲートウェイの層にあたるここで型変換を定義している
  def toUser: Either[UserEntityError ,User] = {
    roleId match {
      case 1 => Right(User(UserId(id), UserName(name), Admin))
      case 2 => Right(User(UserId(id), UserName(name), Member))
      case other => Left(UserRoleIdUndefined(other))
    }
  }
}

// case class のみを定義した場合に、生成されるコンパニオンオブジェクトには
// FunctionNがミックスインされているため、tupledが使えるが、
// コンパニオンオブジェクトを定義すると、FunctionNも明示的にミックスインする
object UserEntity extends ((String, String, Int) => UserEntity) {

  def fromUser(user: User): UserEntity = {
    val roleId = user.role match {
      case Admin => 1
      case Member => 2
    }
    UserEntity(user.id.value, user.name.value, roleId)
  }
}

sealed abstract class UserEntityError

case class UserRoleIdUndefined(value: Int) extends UserEntityError
