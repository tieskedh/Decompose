//
//  App.swift
//  ios-app
//
//  Created by Arkadii Ivanov on 03/05/2022.
//  Copyright © 2022 Arkadii Ivanov. All rights reserved.
//

import SwiftUI
import Counter

@main
struct IosApp: App {
    
    @StateObject
    private var rootHolder = RootHolder()
    
    var body: some Scene {
        WindowGroup {
            CounterRootView(rootHolder.root)
                .onAppear { LifecycleRegistryExtKt.resume(self.rootHolder.lifecycle) }
                .onDisappear { LifecycleRegistryExtKt.stop(self.rootHolder.lifecycle) }
        }
    }
}

class RootHolder : ObservableObject {
    let lifecycle: LifecycleRegistry
    let root: Root
    
    init() {
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        
        root = RootComponent(
            componentContext: DefaultComponentContext(lifecycle: lifecycle),
            deepLink: RootComponentDeepLinkNone.shared,
            webHistoryController: nil
        )
        
        lifecycle.onCreate()
    }
    
    deinit {
        lifecycle.onDestroy()
    }
}
