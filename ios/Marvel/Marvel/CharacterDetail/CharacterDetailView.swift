//
//  CharacterDetailView.swift
//  Marvel
//
//  Created by jmlb23 on 3/5/22.
//

import Mvvm
import SwiftUI

struct CharacterDetailView: View {
    @Binding var id: Int64
    @EnvironmentObject var component: Env

    var viewModel: CharacterViewModel {
        component.component.provideCharacterView
    }

    @State var detail = DomainCharacter(id: 1010903, name: "", description: "", image: "", comics: [])

    var body: some View {
        GeometryReader { geo in
            VStack {
                AsyncImage(url: URL(string: detail.image.replacingOccurrences(of: "http", with: "https"))) { image in
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(maxWidth: .infinity, maxHeight: geo.size.height * 0.5)
                } placeholder: {
                    ProgressView()
                }.frame(maxWidth: .infinity, maxHeight: geo.size.height * 0.5)
                Spacer()
                Text(detail.name).font(.title).frame(maxWidth: .infinity, alignment: .leading).padding()
                Text(detail.description_).font(.body).frame(maxWidth: .infinity, alignment: .leading).padding()
                ScrollView {
                    LazyVStack {
                        ForEach(0 ..< detail.comics.count, id: \.self) { index in
                            let item = detail.comics[index]
                            Text(item.name)
                        }
                    }
                }
            }
        }.frame(maxWidth: .infinity, maxHeight: .infinity).onAppear {
            viewModel.getDetail(id: id)
            viewModel.detail(callback: {
                detail = $0
                debugPrint(">>>>>>> ",$0.comics)
            }, errorCallback: { _ in

            })
        }.onDisappear(perform: {
            viewModel.onCleared()
        })
    }
}

struct CharacterDetailView_Previews: PreviewProvider {
    static var previews: some View {
        CharacterDetailView(id: Binding.constant(1010903)).environmentObject(Env(IosComponent()))
    }
}
