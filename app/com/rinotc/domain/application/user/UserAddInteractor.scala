package com.rinotc.domain.application.user

import java.util.UUID

import com.rinotc.domain.model.user.{User, UserId, UserName, UserRepository}
import com.rinotc.usecases.user.add.{UserAddInputData, UserAddOutputData, UserAddUseCase}
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class UserAddInteractor @Inject()(protected val userRepository: UserRepository)
                                 (implicit ec: ExecutionContext) extends UserAddUseCase {
  override def handle(inputData: UserAddInputData): UserAddOutputData = {
    val uuid = UUID.randomUUID().toString

    val user = User(UserId(uuid), UserName(inputData.userName), inputData.role)

    val output = userRepository.save(user).map { _ => uuid }

    UserAddOutputData(output)
  }
}
