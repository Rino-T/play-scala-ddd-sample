package com.rinotc.usecases.core

abstract class OutputData

abstract class InputData[TOutputData <: OutputData]

abstract class UseCase[TInputData <: InputData[TOutputData], TOutputData <: OutputData] {
  def handle(inputData: TInputData)
}