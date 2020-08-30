package com.rinotc.usecases.user.add

import com.rinotc.domain.model.user.UserRole
import com.rinotc.usecases.core.{InputData, OutputData, UseCase}

trait UserAddUseCase extends UseCase[UserAddInputData, UserAddOutputData]

case class UserAddOutputData(userId: String) extends OutputData

case class UserAddInputData(userName: String, role: UserRole) extends InputData[UserAddOutputData]