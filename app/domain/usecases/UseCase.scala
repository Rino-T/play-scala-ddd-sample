package domain.usecases

import scala.language.higherKinds

abstract class UseCase[TInput <: Input[TOutput], TOutput <: Output, F[_]] {
  def handle(input: TInput): F[TOutput]
}
