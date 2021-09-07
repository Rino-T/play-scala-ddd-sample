package domain.model.user

import domain.model.EntityId

import java.util.UUID

case class UserId(value: UUID) extends EntityId[UUID]
