import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.ApiRepositoryComponent
import di.ApplicationComponent
import di.create

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "VetFDA Portal") {
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}

actual fun getAppComp(): ApplicationComponent {
    return ApplicationComponent::class.create(
        ApiRepositoryComponent::class.create()
    )
}