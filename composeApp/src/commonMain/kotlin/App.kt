import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import di.ApplicationComponent
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import ui.AppTheme
import ui.screens.AdvancedSearchScreen
import ui.screens.MainPortalScreen
import ui.screens.RequestFeatureScreen
import ui.screens.SavedSearchesScreen
import ui.screens.SpecialReportsScreen

expect fun getAppComp(): ApplicationComponent
val appComp: ApplicationComponent = getAppComp()

object Disclaimer {
    var disclaimerShown: Boolean = false
}

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()

        AppTheme {
            NavHost(
                navigator = navigator,
                initialRoute = "/home"
            ) {
                scene("/home") {
                    MainPortalScreen(
                        navigator,
                        appComp.mainPortalViewModel
                    )
                }
                scene("/advanced") {
                    AdvancedSearchScreen(
                        onNav = {navigator.goBack()},
                        appComp.advancedSearchViewModel,
                        appComp.advancedSearchResultsViewModel
                    )
                }
                scene("/saved") {
                    SavedSearchesScreen(
                        onNav = {navigator.goBack()},
                        viewModel = appComp.savedSearchesViewModel
                    )
                }
                scene("/special") {
                    SpecialReportsScreen(
                        onNav = {navigator.goBack()},
                        viewModel = appComp.specialReportsViewModel
                    )
                }
                scene("/feature") {
                    RequestFeatureScreen(
                        onNav = {navigator.goBack()},
                    )
                }
            }
        }
    }
}