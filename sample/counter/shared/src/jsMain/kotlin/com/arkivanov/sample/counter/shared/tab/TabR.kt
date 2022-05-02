package com.arkivanov.sample.counter.shared.tab

import com.arkivanov.sample.counter.shared.RProps
import com.arkivanov.sample.counter.shared.counter.CounterR
import com.arkivanov.sample.counter.shared.uniqueKey
import com.arkivanov.sample.counter.shared.useAsState
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

val TabR: FC<RProps<Tab>> = FC { props ->
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

            CounterR {
                component = leftRouterState.activeChild.instance
                key = component.uniqueKey()
            }

            CounterR {
                component = rightRouterState.activeChild.instance
                key = component.uniqueKey()
            }
        }
    }
}
