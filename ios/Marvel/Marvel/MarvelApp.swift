//
//  MarvelApp.swift
//  Marvel
//
//  Created by jmlb23 on 11/4/22.
//

import SwiftUI
import Mvvm

@main
struct MarvelApp: App {
    
    init(){
        DIIosKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            CharactersView().environmentObject(Env(IosComponent()))
        }
    }
}

class Env : ObservableObject {
    public let component: IosComponent
    
    init(_ component: IosComponent){
        self.component = component
    }
}
