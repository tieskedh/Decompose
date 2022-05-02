//
//  CounterInnerView.swift
//  ios-app
//
//  Created by Arkadii Ivanov on 9/12/20.
//  Copyright © 2020 Arkadii Ivanov. All rights reserved.
//

import SwiftUI
import Counter

struct CounterTabView: View {
    private let tab: Tab
    
    @ObservedObject
    private var firstRouterState: ObservableValue<RouterState<AnyObject, Counter>>
    
    @ObservedObject
    private var secondRouterState: ObservableValue<RouterState<AnyObject, Counter>>
    
    private var firstActiveChild: Counter { firstRouterState.value.activeChild.instance }
    private var secondActiveChild: Counter { secondRouterState.value.activeChild.instance }
    
    init(_ tab: Tab) {
        self.tab = tab
        firstRouterState = ObservableValue(tab.firstRouterState)
        secondRouterState = ObservableValue(tab.secondRouterState)
    }
    
    var body: some View {
        VStack(spacing: 8) {
            CounterView(firstRouterState.value.activeChild.instance)
            CounterView(secondRouterState.value.activeChild.instance)
        }
    }
}

struct CounterTabView_Previews: PreviewProvider {
    static var previews: some View {
        CounterTabView(TabPreview())
    }
}

class TabPreview : Tab {
    let firstRouterState: Value<RouterState<AnyObject, Counter>> = simpleRouterState(CounterPreview())
    let secondRouterState: Value<RouterState<AnyObject, Counter>> = simpleRouterState(CounterPreview())
}
