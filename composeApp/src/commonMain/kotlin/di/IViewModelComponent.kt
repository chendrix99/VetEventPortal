package di

import ui.viewmodels.AdvancedSearchViewModel
import ui.viewmodels.MainPortalViewModel
import ui.viewmodels.RequestFeatureViewModel
import ui.viewmodels.SavedSearchesViewModel
import ui.viewmodels.SpecialReportsViewModel

interface IViewModelComponent {
    val mainPortalViewModel: MainPortalViewModel

    val advancedSearchViewModel: AdvancedSearchViewModel

    val requestFeatureViewModel: RequestFeatureViewModel

    val savedSearchesViewModel: SavedSearchesViewModel

    val specialReportsViewModel: SpecialReportsViewModel
}