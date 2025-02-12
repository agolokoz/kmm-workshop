import UIKit
import common

final class MainViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        
        let label = UILabel()
        label.text = TestKt.getTestString()
        label.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(label)
        NSLayoutConstraint.activate([
            label.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            label.centerYAnchor.constraint(equalTo: view.centerYAnchor),
        ])
        
        label.text = "Timestamp: \(TimeKt.getCurrentTimestamp())"
        
        do {
            let path = try DerivationPath(path: "m/44'/60'/0/0/0")
            label.text = path.getIndices().toIntArray()
                .map { "\($0)" }
                .joined(separator: "/")
            
            label.text = path.children.map { child in
                "\(child.srcIndex)" + (child.hardened ? "'" : "")
            }.joined(separator: "/")
        } catch let error as NSError {
            switch error.kotlinException {
            case let exc as KotlinIllegalArgumentException:
                label.text = "Exception: \(exc.message ?? "")"
            default:
                label.text = "Unknown exception: \(error)"
            }
        }
        
        Task {
            let response = try? await AsyncKt.getResponse(userId: 1, imageId: "1")
            if let response = response {
                await MainActor.run { [weak label] in
                    label?.text = response
                }
            }
        }
    }
}
