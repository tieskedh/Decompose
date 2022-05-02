//
//  CounterRootView.swift
//  ios-app
//
//  Created by Arkadii Ivanov on 9/12/20.
//  Copyright © 2020 Arkadii Ivanov. All rights reserved.
//

import SwiftUI
import Counter

struct CounterRootView: View {
    private let root: Root
    
    @ObservedObject
    private var routerState: ObservableValue<RouterState<AnyObject, RootChild>>
    
    private var activeChild: RootChild { routerState.value.activeChild.instance }
    
    init(_ root: Root) {
        self.root = root
        routerState = ObservableValue(root.routerState)
    }
    
    var body: some View {
        VStack(spacing: 16) {
            CounterTabView(activeChild.tab)
            
            HStack(spacing: 16) {
                Button(action: root.onTabAClicked) {
                    Label("TabA", systemImage: "a.square.fill")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(activeChild is RootChild.TabA ? 1 : 0.5)
                }
                
                Button(action: root.onTabBClicked) {
                    Label("TabB", systemImage: "b.square.fill")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(activeChild is RootChild.TabB ? 1 : 0.5)
                }
                
                Button(action: root.onTabCClicked) {
                    Label("TabC", systemImage: "c.square.fill")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(activeChild is RootChild.TabC ? 1 : 0.5)
                }
            }
        }
    }
}

private struct VerticalLabelStyle: LabelStyle {
    func makeBody(configuration: Configuration) -> some View {
        VStack(alignment: .center, spacing: 8) {
            configuration.icon
            configuration.title
        }
    }
}

struct CounterRootView_Previews: PreviewProvider {
    static var previews: some View {
        CounterRootView(RootPreview())
    }
}

class RootPreview : Root {
    let routerState: Value<RouterState<AnyObject, RootChild>> = simpleRouterState(RootChild.TabA(tab: TabPreview()))
    
    func onTabAClicked() {}
    func onTabBClicked() {}
    func onTabCClicked() {}
}
