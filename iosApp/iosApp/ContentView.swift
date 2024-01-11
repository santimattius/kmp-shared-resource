import SwiftUI
import Shared


struct ContentView: View {
    var body: some View {
        
        VStack {
            Image(uiImage:MR.images.shared.compose_logo.toUIImage()!)
                .resizable()
                .frame(width: 100, height: 100)
            Text("SwiftUI: \(Greeting().greet())")
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

