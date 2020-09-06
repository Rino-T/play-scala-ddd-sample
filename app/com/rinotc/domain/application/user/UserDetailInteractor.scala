package com.rinotc.domain.application.user

import com.rinotc.domain.model.user.UserRepository
import com.rinotc.usecases.user.detail.{UserDetailInputData, UserDetailOutputData, UserDetailUseCase}
import javax.inject.Inject

import scala.concurrent.ExecutionContext

class UserDetailInteractor @Inject()(protected val userRepository: UserRepository)
                                    (implicit ec: ExecutionContext) extends UserDetailUseCase {
  override def handle(inputData: UserDetailInputData): UserDetailOutputData = {
    val userId = inputData.userId
    val output = userRepository.find(userId).map {
      case Right(user) => Some(user)
      case Left(_) => None
    }

    UserDetailOutputData(output)
  }
}
