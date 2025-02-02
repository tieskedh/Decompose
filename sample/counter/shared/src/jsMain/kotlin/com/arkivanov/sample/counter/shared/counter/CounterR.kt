package com.arkivanov.sample.counter.shared.counter

import com.arkivanov.sample.counter.shared.RProps
import com.arkivanov.sample.counter.shared.useAsState
import mui.material.Paper
import mui.material.PaperVariant
import mui.material.Typography
import mui.material.TypographyAlign
import react.FC

val CounterR: FC<RProps<Counter>> = FC { props ->
    val model by props.component.model.useAsState()

    Paper {
        variant = PaperVariant.outlined
        sx = props.sx

        Typography {
            align = TypographyAlign.center
            +model.text
        }
    }
}
