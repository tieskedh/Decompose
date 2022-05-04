package com.arkivanov.sample.shared

import mui.system.PropsWithSx

external interface RProps<T : Any> : PropsWithSx {
    var component: T
}
