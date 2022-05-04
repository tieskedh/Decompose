package com.arkivanov.sample.shared.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.sample.shared.counters.Counters

interface Root {

    val routerState: Value<RouterState<*, Child>>

    fun onCountersTabClicked()
    fun onMultiPaneTabClicked()
    fun onDynamicFeaturesTabClicked()

    sealed class Child {
        class CountersChild(val counters: Counters) : Child()
        class MultiPaneChild(val counters: Counters) : Child()
        class DynamicFeaturesChild(val counters: Counters) : Child()
    }
}
