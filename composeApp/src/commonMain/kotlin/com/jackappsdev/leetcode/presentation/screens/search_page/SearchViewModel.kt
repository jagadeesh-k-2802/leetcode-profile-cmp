package com.jackappsdev.leetcode.presentation.screens.search_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackappsdev.leetcode.domain.usecase.GetCombinedProfileUseCase
import com.jackappsdev.leetcode.presentation.base.EventDrivenViewModel
import com.jackappsdev.leetcode.presentation.screens.search_page.event.SearchEffect
import com.jackappsdev.leetcode.presentation.screens.search_page.event.SearchEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class SearchViewModel(
    private val getCombinedProfile: GetCombinedProfileUseCase
): ViewModel(), EventDrivenViewModel<SearchState, SearchEvent, SearchEffect> {

    private val _state = MutableStateFlow(SearchState())
    override val state: StateFlow<SearchState> = _state.asStateFlow()

    private val _effect = Channel<SearchEffect>()
    override val effectFlow = _effect.receiveAsFlow()

    private fun onSearchQueryChange(query: String) {
        _state.value = _state.value.copy(searchQuery = query, canSubmitData = query.isNotBlank())
    }

    private suspend fun onSearch() {
        _state.value = _state.value.copy(isLoading = true, isError = false, data = null)
        getCombinedProfile(username = state.value.searchQuery).collectLatest { result ->
            result.fold(
                onSuccess = { profile ->
                    _state.value = _state.value.copy(
                        data = profile,
                        isLoading = false,
                        isError = false
                    )
                },
                onFailure = { error ->
                    error.printStackTrace()
                    _state.value = _state.value.copy(
                        data = null,
                        isLoading = false,
                        isError = true
                    )
                }
            )
        }
    }

    private suspend fun refresh() {
        if (state.value.canSubmitData) {
            onSearch()
        } else {
            _state.value = _state.value.copy(data = null, isError = false, isLoading = false)
        }
    }

    override fun onEvent(event: SearchEvent) {
        viewModelScope.launch {
            when (event) {
                is SearchEvent.OnSearchQueryChange -> onSearchQueryChange(event.query)
                is SearchEvent.OnSearch -> onSearch()
                is SearchEvent.OnRefresh -> refresh()
            }
        }
    }
}
