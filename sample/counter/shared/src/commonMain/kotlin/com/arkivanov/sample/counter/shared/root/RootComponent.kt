package com.arkivanov.sample.counter.shared.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.bringToFront
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.router.webhistory.WebHistoryController
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.sample.counter.shared.root.Root.Child
import com.arkivanov.sample.counter.shared.tab.TabComponent

@OptIn(ExperimentalDecomposeApi::class)
class RootComponent constructor(
    componentContext: ComponentContext,
    deepLink: DeepLink = DeepLink.None,
    webHistoryController: WebHistoryController? = null,
) : Root, ComponentContext by componentContext {

    private val router: Router<Config, Child> =
        router(
            initialStack = { getInitialStack(deepLink) },
            handleBackButton = true,
            childFactory = ::child,
        )

    override val routerState: Value<RouterState<*, Child>> = router.state

    init {
        webHistoryController?.attach(
            router = router,
            getPath = { config ->
                when (config) {
                    Config.TabA -> "/a"
                    Config.TabB -> "/b"
                    Config.TabC -> "/c"
                }
            },
            getConfiguration = {
                when (it.removePrefix("/")) {
                    "a" -> Config.TabA
                    "b" -> Config.TabB
                    "c" -> Config.TabC
                    else -> Config.TabA
                }
            },
        )
    }

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.TabA -> Child.TabA(TabComponent(componentContext))
            is Config.TabB -> Child.TabB(TabComponent(componentContext))
            is Config.TabC -> Child.TabC(TabComponent(componentContext))
        }

    override fun onTabAClicked() {
        router.bringToFront(Config.TabA)
    }

    override fun onTabBClicked() {
        router.bringToFront(Config.TabB)
    }

    override fun onTabCClicked() {
        router.bringToFront(Config.TabC)
    }

    private companion object {
        private fun getInitialStack(deepLink: DeepLink): List<Config> =
            when (deepLink) {
                is DeepLink.None -> listOf(Config.TabA)

                is DeepLink.Web ->
                    listOf(
                        when (deepLink.path.removePrefix("/")) {
                            "a" -> Config.TabA
                            "b" -> Config.TabB
                            "c" -> Config.TabC
                            else -> Config.TabA
                        }
                    )
            }
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        object TabA : Config

        @Parcelize
        object TabB : Config

        @Parcelize
        object TabC : Config
    }

    sealed interface DeepLink {
        object None : DeepLink
        class Web(val path: String) : DeepLink
    }
}
