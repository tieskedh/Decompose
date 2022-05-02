package com.arkivanov.sample.counter.shared.ui.compose

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.childAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.fade
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.sample.counter.shared.counter.Counter
import com.arkivanov.sample.counter.shared.tab.Tab

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun TabContent(tab: Tab, modifier: Modifier = Modifier) {
    ColumnOrRow(modifier = modifier) {
        Children(
            routerState = tab.firstRouterState,
            animation = childAnimation(fade()),
        ) {
            CounterContent(counter = it.instance)
        }

        Children(
            routerState = tab.secondRouterState,
            animation = childAnimation(fade()),
        ) {
            CounterContent(counter = it.instance)
        }
    }
}

@Composable
private fun ColumnOrRow(modifier: Modifier, content: @Composable () -> Unit) {
    Log.d("MyTest", "Or: ${LocalContext.current.resources.configuration.orientation}")
    if (LocalContext.current.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) { content() }
    } else {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) { content() }
    }
}

@Preview(showBackground = true)
@Composable
fun TabContentPreview() {
    TabContent(tab = TabPreview())
}

internal class TabPreview : Tab {
    override val firstRouterState: Value<RouterState<*, Counter>> =
        MutableValue(
            RouterState(
                configuration = Unit,
                instance = CounterPreview(),
            )
        )

    override val secondRouterState: Value<RouterState<*, Counter>> =
        MutableValue(
            RouterState(
                configuration = Unit,
                instance = CounterPreview(),
            )
        )
}
