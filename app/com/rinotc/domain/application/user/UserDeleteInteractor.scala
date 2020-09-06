package com.rinotc.domain.application.user

import com.rinotc.domain.model.user.UserRepository
import com.rinotc.usecases.user.delete.{UserDeleteInputData, UserDeleteOutputData, UserDeleteUseCase}
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class UserDeleteInteractor @Inject()(protected val userRepository: UserRepository)
                                    (implicit ec: ExecutionContext) extends UserDeleteUseCase {
  override def handle(inputData: UserDeleteInputData): UserDeleteOutputData = {
    val output = userRepository.find(inputData.userId).flatMap {
      case Right(user) => userRepository.remove(user).map(value => Right(value))
      case Left(error) => Future.successful(Left(error))
    }

    UserDeleteOutputData(output)
  }
}
