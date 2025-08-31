package com.jackappsdev.leetcode.presentation.screens.search_page.event

sealed class SearchEvent {
    class OnSearchQueryChange(val query: String) : SearchEvent()
    object OnSearch : SearchEvent()
    object OnRefresh : SearchEvent()
}
