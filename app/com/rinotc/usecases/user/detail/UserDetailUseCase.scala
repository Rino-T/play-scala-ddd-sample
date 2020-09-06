package com.rinotc.usecases.user.detail

import com.rinotc.domain.model.user.{User, UserId}
import com.rinotc.usecases.core.{InputData, OutputData, UseCase}

import scala.concurrent.Future

trait UserDetailUseCase extends UseCase[UserDetailInputData, UserDetailOutputData]

case class UserDetailOutputData(user: Future[Option[User]]) extends OutputData

case class UserDetailInputData(userId: UserId) extends InputData[UserDetailOutputData]
