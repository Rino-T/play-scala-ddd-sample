package com.rinotc.domain.application.user

import com.rinotc.domain.model.user.UserRepository
import com.rinotc.usecases.user.list.{UserListInputData, UserListOutputData, UserListUseCase}
import javax.inject.Inject

import scala.concurrent.ExecutionContext

class UserListInteractor @Inject()(protected val userRepository: UserRepository)
                                  (implicit ec: ExecutionContext) extends UserListUseCase {

  override def handle(inputData: UserListInputData): UserListOutputData = {
    UserListOutputData(userRepository.findAll)
  }
}
