package com.arkivanov.sample.shared

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver
import react.StateInstance
import react.useEffectOnce
import react.useState

internal fun <T : Any> Value<T>.useAsState(): StateInstance<T> {
    val state = useState { value }
    val (_, set) = state

    useEffectOnce {
        val observer: ValueObserver<T> = { set(it) }
        subscribe(observer)
        cleanup { unsubscribe(observer) }
    }

    return state
}
