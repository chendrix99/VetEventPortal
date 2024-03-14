import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import di.ApplicationComponent
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import ui.screens.AdvancedSearchResultsScreen
import ui.screens.AdvancedSearchScreen
import ui.screens.MainPortalScreen
import ui.screens.RequestFeatureScreen
import ui.screens.SavedSearchesScreen
import ui.screens.SearchResultInfoScreen
import ui.screens.SpecialReportsScreen

expect fun getAppComp(): ApplicationComponent

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()

        val appComp: ApplicationComponent = getAppComp()

        MaterialTheme {
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
                        navigator,
                        appComp.advancedSearchViewModel
                    )
                }
                scene("/advancedresults") {
                    AdvancedSearchResultsScreen(
                        navBack = {navigator.goBack()}
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
                        viewModel = appComp.requestFeatureViewModel
                    )
                }
                // TODO Correct way to to this? Need to pass anything?
                scene("/searchinfo") {
                    SearchResultInfoScreen(
                        onNav = {navigator.goBack()}
                    )
                }
            }
        }
    }
}