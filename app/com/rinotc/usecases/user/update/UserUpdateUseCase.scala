package com.rinotc.usecases.user.update

import com.rinotc.domain.model.user.{UserId, UserName, UserRepositoryError}
import com.rinotc.usecases.core.{InputData, OutputData, UseCase}

import scala.concurrent.Future

trait UserUpdateUseCase extends UseCase[UserUpdateInputData, UserUpdateOutputData]

case class UserUpdateOutputData(output: Future[Either[UserRepositoryError, Int]]) extends OutputData

case class UserUpdateInputData(userId: UserId, name: UserName) extends InputData[UserUpdateOutputData]