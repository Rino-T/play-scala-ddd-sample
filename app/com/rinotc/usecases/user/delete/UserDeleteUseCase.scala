package com.rinotc.usecases.user.delete

import com.rinotc.domain.model.user.{UserId, UserRepositoryError}
import com.rinotc.usecases.core.{InputData, OutputData, UseCase}

import scala.concurrent.Future

trait UserDeleteUseCase extends UseCase[UserDeleteInputData, UserDeleteOutputData]

case class UserDeleteOutputData(output: Future[Either[UserRepositoryError, Int]]) extends OutputData

case class UserDeleteInputData(userId: UserId) extends InputData[UserDeleteOutputData]