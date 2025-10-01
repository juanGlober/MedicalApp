package com.example.medicalapp.domain.usecase.base

interface BaseUseCase<in Params, out Result> {
    suspend operator fun invoke(params: Params): Result
}

interface NoParamsUseCase<out Result> {
    suspend operator fun invoke(): Result
}
