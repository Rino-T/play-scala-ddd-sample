package domain.model.user

import domain.model.Entity
import domain.valueObjects.person.FullName
import domain.valueObjects.role.Role

final class User(
    val id: UserId,
    val name: FullName,
    val role: Role
) extends Entity[UserId] {

  override def canEqual(that: Any): Boolean = that.isInstanceOf[User]

  def renameTo(name: FullName): User = new User(this.id, name, this.role)
}
