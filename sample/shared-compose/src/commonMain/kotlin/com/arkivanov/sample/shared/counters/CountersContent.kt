package com.arkivanov.sample.shared.counters

//import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.sample.shared.counters.counter.Counter
import com.arkivanov.sample.shared.counters.counter.CounterContent
import com.arkivanov.sample.shared.counters.counter.CounterPreview

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun CountersContent(counters: Counters, modifier: Modifier = Modifier) {
    ColumnOrRow(modifier = modifier) {
        Children(routerState = counters.firstRouterState) {
            CounterContent(counter = it.instance)
        }

        Children(routerState = counters.secondRouterState) {
            CounterContent(counter = it.instance)
        }
    }
}

@Composable
private fun ColumnOrRow(modifier: Modifier, content: @Composable () -> Unit) {
//    if (LocalContext.current.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//        Row(
//            modifier = modifier,
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalAlignment = Alignment.CenterVertically,
//        ) { content() }
//    } else {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) { content() }
//    }
}

@Preview
@Composable
internal fun TabContentPreview() {
    CountersContent(counters = CountersPreview())
}

class CountersPreview : Counters {
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
