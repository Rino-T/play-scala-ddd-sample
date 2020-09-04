package com.rinotc.usecases.core


trait OutputData

trait InputData[TOutputData <: OutputData]

trait UseCase[TInputData <: InputData[TOutputData], TOutputData <: OutputData] {
  def handle(inputData: TInputData): TOutputData
}