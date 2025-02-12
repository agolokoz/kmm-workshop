import common

extension Int {
    
    func toInt32() -> Int32 {
        return Int32(self)
    }
}

extension Int32 {
    
    func toInt() -> Int {
        return Int(self)
    }
}

extension KotlinIntArray {
    
    func toIntArray() -> [Int] {
        let size = Int(self.size)
        var array = [Int](repeating: 0, count: size)
        for i in 0..<size {
            array[i] = self.get(index: i.toInt32()).toInt()
        }
        return array
    }
}
