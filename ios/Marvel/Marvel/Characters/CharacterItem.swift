//
//  CharacterItem.swift
//  Marvel
//
//  Created by jmlb23 on 2/5/22.
//

import Foundation
import SwiftUI

struct CharacterItem: View {
    let url: String
    let name: String
    let description: String
    var body: some View {
            HStack {
                AsyncImage(url: URL(string: url)){ image in
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(maxWidth: 100)
                } placeholder: {
                    ProgressView()
                }

                
                VStack {
                    Text(name)
                    Spacer()
                    Text(description).font(Font.caption)
                }.frame(maxWidth: .infinity)
            }.frame(maxWidth: .infinity).background(Color.white)
                .clipShape(RoundedRectangle(cornerRadius: 5))
                .shadow(color: .gray, radius: 1, x: 1, y: 1)
                .padding(.leading, 10)
                .padding(.trailing, 10)
        }
}

struct CharacterItem_Preview: PreviewProvider {
    static var previews: some View {
        CharacterItem(url: "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg", name: "A BOMB (HAS)", description: "Rick Jones has been Hulk's best bud since day one, but now he's more than a friend...he's a teammate! Transformed by a Gamma energy explosion, A-Bomb's thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!")
    }
}
