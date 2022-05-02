package com.arkivanov.sample.counter.shared.ui.android

import android.view.View
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.android.RouterView
import com.arkivanov.decompose.extensions.android.ViewContext
import com.arkivanov.decompose.extensions.android.layoutInflater
import com.arkivanov.decompose.value.observe
import com.arkivanov.sample.counter.shared.R
import com.arkivanov.sample.counter.shared.root.Root
import com.google.android.material.bottomnavigation.BottomNavigationView

@ExperimentalDecomposeApi
@Suppress("FunctionName") // Factory function
fun ViewContext.RootView(root: Root): View {
    val layout = layoutInflater.inflate(R.layout.counter_root, parent, false)
    val router: RouterView = layout.findViewById(R.id.router)

    router.children(root.routerState, lifecycle) { parent, child, _ ->
        parent.removeAllViews()
        parent.addView(TabView(child.tab))
    }

    val navigationView: BottomNavigationView = layout.findViewById(R.id.navigation_view)

    val listener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (val id = item.itemId) {
                R.id.tab_a -> root.onTabAClicked()
                R.id.tab_b -> root.onTabBClicked()
                R.id.tab_c -> root.onTabCClicked()
                else -> error("Unrecognized item id: $id")
            }

            true
        }

    navigationView.setOnNavigationItemSelectedListener(listener)

    root.routerState.observe(lifecycle) { state ->
        navigationView.setOnNavigationItemSelectedListener(null)

        navigationView.selectedItemId =
            when (state.activeChild.instance) {
                is Root.Child.TabA -> R.id.tab_a
                is Root.Child.TabB -> R.id.tab_b
                is Root.Child.TabC -> R.id.tab_c
            }

        navigationView.setOnNavigationItemSelectedListener(listener)
    }

    return layout
}
