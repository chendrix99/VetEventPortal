package di

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope
import moe.tlaster.precompose.stateholder.LocalSavedStateHolder
import moe.tlaster.precompose.stateholder.SavedStateHolder

@Scope
annotation class ApplicationScope

@Component
@ApplicationScope
abstract class ApplicationComponent(
    @Component val apiRepositoryComponent: ApiRepositoryComponent,
    @Component val databaseRepositoryComponent: DatabaseRepositoryComponent
): IViewModelComponent {}