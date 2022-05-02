package com.arkivanov.sample.counter.shared.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.sample.counter.shared.R
import com.arkivanov.sample.counter.shared.root.Root

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(root: Root) {
    val routerState by root.routerState.subscribeAsState()
    val activeComponent = routerState.activeChild.instance

    Column {
        TabContent(
            modifier = Modifier.fillMaxWidth().weight(weight = 1F),
            tab = activeComponent.tab,
        )

        BottomNavigation(modifier = Modifier.fillMaxWidth()) {
            BottomNavigationItem(
                selected = activeComponent is Root.Child.TabA,
                onClick = root::onTabAClicked,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_one),
                        contentDescription = "Tab A",
                    )
                },
                label = { Text(text = "TabA") },
            )

            BottomNavigationItem(
                selected = activeComponent is Root.Child.TabB,
                onClick = root::onTabBClicked,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_two),
                        contentDescription = "Tab B",
                    )
                },
                label = { Text(text = "TabB") },
            )

            BottomNavigationItem(
                selected = activeComponent is Root.Child.TabC,
                onClick = root::onTabCClicked,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_three),
                        contentDescription = "Tab C",
                    )
                },
                label = { Text(text = "TabC") },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RootContentPreview() {
    RootContent(RootPreview())
}

class RootPreview : Root {
    override val routerState: Value<RouterState<*, Root.Child>> =
        MutableValue(
            RouterState(
                configuration = Unit,
                instance = Root.Child.TabB(tab = TabPreview()),
            )
        )

    override fun onTabAClicked() {}
    override fun onTabBClicked() {}
    override fun onTabCClicked() {}
}
