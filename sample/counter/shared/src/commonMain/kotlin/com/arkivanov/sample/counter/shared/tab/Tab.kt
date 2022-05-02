package com.arkivanov.sample.counter.shared.tab

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.arkivanov.sample.counter.shared.counter.Counter

interface Tab {

    val firstRouterState: Value<RouterState<*, Counter>>
    val secondRouterState: Value<RouterState<*, Counter>>
}
