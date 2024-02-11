import SwiftUI
import Shared

@main
struct iOSApp: App {
    let sdk = SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory())
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
