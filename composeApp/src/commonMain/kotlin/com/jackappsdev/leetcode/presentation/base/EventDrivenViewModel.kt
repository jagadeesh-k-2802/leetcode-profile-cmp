package com.jackappsdev.leetcode.presentation.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface EventDrivenViewModel<State, Event, Effect> {
    val state: StateFlow<State>
    val effectFlow: Flow<Effect>
    fun onEvent(event: Event)
}
