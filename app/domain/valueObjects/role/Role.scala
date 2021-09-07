package domain.valueObjects.role

sealed trait Role

object Role {
  case object Admin  extends Role
  case object Member extends Role
}
