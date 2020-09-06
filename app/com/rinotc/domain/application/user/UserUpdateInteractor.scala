package com.rinotc.domain.application.user

import com.rinotc.domain.model.user.UserRepository
import com.rinotc.usecases.user.update.{UserUpdateInputData, UserUpdateOutputData, UserUpdateUseCase}
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class UserUpdateInteractor @Inject()(protected val userRepository: UserRepository)
                                    (implicit ec: ExecutionContext) extends UserUpdateUseCase {
  override def handle(inputData: UserUpdateInputData): UserUpdateOutputData = {
    val output = userRepository.find(inputData.userId).flatMap {
      case Right(user) =>
        val updatedUser = user.renameTo(inputData.name)
        userRepository.save(updatedUser).map(value => Right(value))
      case Left(error) => Future.successful(Left(error))
    }

    UserUpdateOutputData(output)
  }
}
