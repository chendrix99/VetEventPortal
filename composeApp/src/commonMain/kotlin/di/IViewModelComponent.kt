package di

import ui.viewmodels.AdvancedSearchResultsViewModel
import ui.viewmodels.AdvancedSearchViewModel
import ui.viewmodels.MainPortalViewModel
import ui.viewmodels.SavedSearchesViewModel
import ui.viewmodels.SpecialReportsViewModel

interface IViewModelComponent {
    @ApplicationScope val mainPortalViewModel: MainPortalViewModel

    @ApplicationScope val advancedSearchViewModel: AdvancedSearchViewModel

    @ApplicationScope val savedSearchesViewModel: SavedSearchesViewModel

    @ApplicationScope val specialReportsViewModel: SpecialReportsViewModel

    @ApplicationScope val advancedSearchResultsViewModel: AdvancedSearchResultsViewModel
}