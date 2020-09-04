package com.rinotc.usecases.user.list

import com.rinotc.domain.model.user.User
import com.rinotc.usecases.core.{InputData, OutputData, UseCase}

import scala.concurrent.Future


abstract class UserListUseCase extends UseCase[UserListInputData, UserListOutputData]

class UserListInputData extends InputData[UserListOutputData]

case class UserListOutputData(users: Future[Seq[User]]) extends OutputData