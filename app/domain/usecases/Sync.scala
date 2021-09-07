package domain.usecases

case class Sync[TOutput <: Output](result: TOutput)
