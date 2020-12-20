package com.aru_oi.cleanarchitecture.domain.usecase.core

// クリーンアーキテクチャのInput Boundaryに該当する
interface IUseCase<TInputData : IInputData> {
    // サンプルコードをいくつか見たところUseCaseクラスにpublicメソッドはほとんどなかった（継承するクラス含め）。
    // InputDataを渡したらOutPutDataを返すメソッド一つだけ、もしくはPresenterのsetter/getterメソッドが加えてあるぐらい。
    // initialize/terminateといったメソッドはサンプルコードでは見かけなかったので、
    // UseCaseクラスでは初期化/終了を意識したコードを書くのはよくないのかもしれない。
    suspend fun execute(inputData: TInputData)
}
