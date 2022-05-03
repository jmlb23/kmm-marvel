//
//  ContentView.swift
//  Marvel
//
//  Created by jmlb23 on 11/4/22.
//

import Mvvm
import SwiftUI

struct CharactersView: View {
    @EnvironmentObject var component: Env

    var viewModel: CharactersViewModel {
        component.component.provideCharacterListView
    }

    @State var elements: [DomainCharacter] = []
    @State var isPresented = false
    @State var selectedItem: Int64 = 0
    var body: some View {
        GeometryReader { reader in
            ScrollView {
                LazyVStack {
                    ForEach(0 ..< elements.count, id: \.self) { index in
                        let item = elements[index]
                        CharacterItem(url: item.image.replacingOccurrences(of: "http", with: "https"), name: item.name, description: item.description_)
                            .frame(maxHeight: reader.size.height * 0.30).onTapGesture {
                                selectedItem = item.id
                                isPresented.toggle()
                            }.onAppear{
                                if index == (elements.count - 1) {
                                    viewModel.nextPage()
                                }
                            }
                    }
                    
                    
                }
            }.onAppear(perform: {
                viewModel.nextPage()
                viewModel.getElement { x in
                    elements = x
                }
            }).onDisappear(perform: {
                viewModel.onCleared()
            }).sheet(isPresented: $isPresented){
                CharacterDetailView(id: $selectedItem)
            }
        }
    }
}

struct CharactersView_Previews: PreviewProvider {
    static var previews: some View {
        CharactersView().environmentObject(Env(IosComponent()))
    }
}
