package domain.usecases.user.add

import domain.usecases.UseCase

import scala.concurrent.Future

abstract class AddUserUseCase extends UseCase[AddUserInput, AddUserOutput, Future]
