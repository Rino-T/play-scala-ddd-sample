package com.rinotc.usecases.user.list

import com.rinotc.usecases.core.{InputData, OutputData, UseCase}
import com.rinotc.usecases.user.common.UserData


class UserListInputData extends InputData[UserListOutputData]

case class UserListOutputData(users: List[UserData]) extends OutputData

abstract class UserListUseCase extends UseCase[UserListInputData, UserListOutputData]