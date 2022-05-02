package com.arkivanov.sample.counter.app

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.router.webhistory.DefaultWebHistoryController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.arkivanov.sample.counter.shared.root.RootComponent
import com.arkivanov.sample.counter.shared.root.RootR
import kotlinx.browser.document
import kotlinx.browser.window
import mui.material.Container
import mui.system.Breakpoint
import org.w3c.dom.Document
import react.create
import react.dom.client.createRoot

fun main() {
    val lifecycle = LifecycleRegistry()

    val root =
        RootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            deepLink = RootComponent.DeepLink.Web(path = window.location.pathname),
            webHistoryController = DefaultWebHistoryController(),
        )

    lifecycle.attachToDocument()

    createRoot(document.getElementById("app")!!).render(
        Container.create {
            maxWidth = Breakpoint.sm

            RootR {
                component = root
            }
        }
    )
}

private fun LifecycleRegistry.attachToDocument() {
    fun onVisibilityChanged() {
        if (document.visibilityState == "visible") {
            resume()
        } else {
            stop()
        }
    }

    onVisibilityChanged()

    document.addEventListener(type = "visibilitychange", callback = { onVisibilityChanged() })
}

private val Document.visibilityState: String get() = asDynamic().visibilityState.unsafeCast<String>()
