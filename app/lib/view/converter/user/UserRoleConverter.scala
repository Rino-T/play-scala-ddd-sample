package lib.view.converter.user

import com.rinotc.domain.model.user.{Admin, Member, UserRole}

object UserRoleConverter {
  def convert(roleId: String): Either[UserRoleConvertError, UserRole] = roleId match {
    case "admin" => Right(Admin)
    case "member" => Right(Member)
    case _ => Left(NoSuchUserRole)
  }

  def convert(role: UserRole): String = role match {
    case Admin => "admin"
    case Member => "member"
  }
}

sealed trait UserRoleConvertError
case object NoSuchUserRole extends UserRoleConvertError
