package di

import data.api.ApiRepository
import data.api.FdaApiClient
import data.api.getHttpClient
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class ApiRepositoryComponent() {
    abstract val repo: ApiRepository

    @Provides
    fun fdaApiClient(): FdaApiClient = FdaApiClient(getHttpClient())
}