package di

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Scope

@Scope
annotation class ApplicationScope

@Component
@ApplicationScope
abstract class ApplicationComponent(
    @Component val apiRepositoryComponent: ApiRepositoryComponent
): IViewModelComponent {}