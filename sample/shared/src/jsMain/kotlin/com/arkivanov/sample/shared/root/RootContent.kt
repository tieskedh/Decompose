package com.arkivanov.sample.shared.root

import com.arkivanov.sample.shared.RProps
import com.arkivanov.sample.shared.counters.CountersContent
import com.arkivanov.sample.shared.root.Root.Child.CountersChild
import com.arkivanov.sample.shared.root.Root.Child.DynamicFeaturesChild
import com.arkivanov.sample.shared.root.Root.Child.MultiPaneChild
import com.arkivanov.sample.shared.useAsState
import csstype.BoxSizing
import csstype.Display
import csstype.FlexDirection
import csstype.number
import csstype.pct
import csstype.px
import mui.material.BottomNavigation
import mui.material.BottomNavigationAction
import mui.material.Box
import mui.material.Icon
import mui.material.Paper
import mui.material.PaperVariant
import mui.system.sx
import react.FC
import react.ReactNode
import react.create

var RootContent: FC<RProps<Root>> = FC { props ->
    val routerState by props.component.routerState.useAsState()

    Paper {
        variant = PaperVariant.outlined

        sx {
            display = Display.inlineBlock
        }

        Box {
            sx {
                display = Display.flex
                flexDirection = FlexDirection.column
            }

            Box {
                sx {
                    padding = 16.px
                    width = 100.pct
                    boxSizing = BoxSizing.borderBox
                }

                when (val child = routerState.activeChild.instance) {
                    is CountersChild ->
                        CountersContent {
                            component = child.counters

                            sx {
                                display = Display.inlineBlock
                                flexGrow = number(1.0)
                                flexShrink = number(0.0)
                            }
                        }

                    is MultiPaneChild -> TODO()
                    is DynamicFeaturesChild -> TODO()
                }.let {}
            }

            BottomNavigation {
                sx {
                    flexGrow = number(0.0)
                }

                showLabels = true

                value = when (routerState.activeChild.instance) {
                    is CountersChild -> TabItem.A
                    is MultiPaneChild -> TabItem.B
                    is DynamicFeaturesChild -> TabItem.C
                }

                onChange = { _, newValue ->
                    when (newValue.unsafeCast<TabItem>()) {
                        TabItem.A -> props.component.onCountersTabClicked()
                        TabItem.B -> props.component.onMultiPaneTabClicked()
                        TabItem.C -> props.component.onDynamicFeaturesTabClicked()
                    }
                }

                BottomNavigationAction {
                    value = TabItem.A
                    label = ReactNode("TabA")
                    icon = Icon.create { +"looks_one" }
                }

                BottomNavigationAction {
                    value = TabItem.B
                    label = ReactNode("TabB")
                    icon = Icon.create { +"looks_two" }
                }

                BottomNavigationAction {
                    value = TabItem.C
                    label = ReactNode("TabC")
                    icon = Icon.create { +"looks_3" }
                }
            }
        }
    }
}

private enum class TabItem {
    A, B, C
}
