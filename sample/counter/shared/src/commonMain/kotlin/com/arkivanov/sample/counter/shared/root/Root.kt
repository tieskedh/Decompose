package com.arkivanov.sample.counter.shared.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.sample.counter.shared.tab.Tab

interface Root {

    val routerState: Value<RouterState<*, Child>>

    fun onTabAClicked()
    fun onTabBClicked()
    fun onTabCClicked()

    sealed class Child {
        abstract val tab: Tab

        class TabA(override val tab: Tab) : Child()
        class TabB(override val tab: Tab) : Child()
        class TabC(override val tab: Tab) : Child()
    }
}
