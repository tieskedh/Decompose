package com.arkivanov.sample.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.sample.app.ui.ComposeAppTheme
import com.arkivanov.sample.shared.counters.Counters
import com.arkivanov.sample.shared.counters.CountersComponent
import com.arkivanov.sample.shared.counters.CountersContent

class MainActivity : AppCompatActivity() {

    private val mode = Mode.COMPOSE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val root = RootComponent(defaultComponentContext())
        val root = CountersComponent(defaultComponentContext())

        when (mode) {
            Mode.COMPOSE -> drawViaCompose(root)
//            Mode.VIEWS -> drawViaViews(root)
        }.let {}
    }

    private fun drawViaCompose(root: Counters) {
        setContent {
            ComposeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CountersContent(counters = root, modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

//    private fun drawViaCompose(root: Root) {
//        setContent {
//            ComposeAppTheme {
//                Surface(color = MaterialTheme.colors.background) {
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        RootContent(root)
//                    }
//                }
//            }
//        }
//    }

//    @OptIn(ExperimentalDecomposeApi::class)
//    private fun drawViaViews(root: Root) {
//        setContentView(R.layout.main_activity)
//
//        val viewContext =
//            DefaultViewContext(
//                parent = findViewById(R.id.content),
//                lifecycle = essentyLifecycle(),
//            )
//
//        viewContext.apply {
//            child(parent) {
//                RootView(root)
//            }
//        }
//    }

    private enum class Mode {
        COMPOSE,// VIEWS
    }
}
