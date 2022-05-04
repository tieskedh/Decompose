package com.arkivanov.sample.shared.counters

import com.arkivanov.sample.shared.RProps
import com.arkivanov.sample.shared.counters.counter.CounterContent
import com.arkivanov.sample.shared.uniqueKey
import com.arkivanov.sample.shared.useAsState
import csstype.BoxSizing
import csstype.px
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Stack
import mui.material.StackDirection
import mui.system.ResponsiveStyleValue
import mui.system.sx
import react.FC
import react.key

val CountersContent: FC<RProps<Counters>> = FC { props ->
    val leftRouterState by props.component.firstRouterState.useAsState()
    val rightRouterState by props.component.secondRouterState.useAsState()

    Paper {
        variant = PaperVariant.outlined
        sx = props.sx

        Stack {
            direction = ResponsiveStyleValue(StackDirection.row)
            spacing = ResponsiveStyleValue(2)

            sx {
                boxSizing = BoxSizing.borderBox
                padding = 16.px
            }

            CounterContent {
                component = leftRouterState.activeChild.instance
                key = component.uniqueKey()
            }

            CounterContent {
                component = rightRouterState.activeChild.instance
                key = component.uniqueKey()
            }
        }
    }
}
