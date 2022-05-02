package com.arkivanov.sample.counter.shared.root

import com.arkivanov.sample.counter.shared.RProps
import com.arkivanov.sample.counter.shared.tab.TabR
import com.arkivanov.sample.counter.shared.uniqueKey
import com.arkivanov.sample.counter.shared.useAsState
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
import react.key

var RootR: FC<RProps<Root>> = FC { props ->
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

                TabR {
                    component = routerState.activeChild.instance.tab
                    key = component.uniqueKey()

                    sx {
                        display = Display.inlineBlock
                        flexGrow = number(1.0)
                        flexShrink = number(0.0)
                    }
                }
            }

            BottomNavigation {
                sx {
                    flexGrow = number(0.0)
                }

                showLabels = true

                value = when (routerState.activeChild.instance) {
                    is Root.Child.TabA -> TabItem.A
                    is Root.Child.TabB -> TabItem.B
                    is Root.Child.TabC -> TabItem.C
                }

                onChange = { _, newValue ->
                    when (newValue.unsafeCast<TabItem>()) {
                        TabItem.A -> props.component.onTabAClicked()
                        TabItem.B -> props.component.onTabBClicked()
                        TabItem.C -> props.component.onTabCClicked()
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
