import di.ApiRepositoryComponent
import di.ApplicationComponent
import di.create

actual fun getAppComp(): ApplicationComponent {
    return ApplicationComponent::class.create(
        ApiRepositoryComponent::class.create()
    )
}