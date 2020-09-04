package com.rinotc.domain.model.user

import scala.concurrent.Future

trait UserRepository {
  def save(user: User): Future[Int]
  def remove(user: User): Future[Int]
  def findAll: Future[Seq[User]]
  def find(id: UserId): Future[Either[UserRepositoryError, User]]
}

sealed trait UserRepositoryError
case object UserNotFound extends UserRepositoryError
case object DatabaseError extends UserRepositoryError