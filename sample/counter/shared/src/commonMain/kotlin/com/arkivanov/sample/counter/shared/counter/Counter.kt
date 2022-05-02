package com.arkivanov.sample.counter.shared.counter

import com.arkivanov.decompose.value.Value

interface Counter {

    val model: Value<Model>

    fun onNextClicked()

    fun onPrevClicked()

    data class Model(
        val title: String,
        val text: String,
        val isBackEnabled: Boolean,
    )
}
