package domain.valueObjects.person

import domain.valueObjects.ValueObject

case class FullName(firstName: String, lastName: String) extends ValueObject {
  def fullName: String = lastName + firstName
}
