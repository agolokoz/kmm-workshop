package dev.glk.common

class DerivationPath(
    val children: List<Child>
) {

    @Throws(IllegalArgumentException::class)
    constructor(path: String): this(fromString(path))

    fun getIndices(): IntArray {
        return IntArray(children.size) { children[it].index }
    }

    private companion object {

        @Throws(IllegalArgumentException::class)
        private fun fromString(string: String): List<Child> {
            val splits = string.split("/")
            val children = mutableListOf<Child>()
            for (i in splits.indices) {
                val split = splits[i]
                val hardened = split.endsWith("'")
                val index = split.substring(0, split.length - if (hardened) 1 else 0).toIntOrNull()
                if (i > 0) {
                    if (index == null) {
                        throw IllegalArgumentException("Invalid derivation path: $string")
                    }
                    children.add(Child(index, hardened))
                }
            }
            return children
        }
    }

    class Child(
        val srcIndex: Int,
        val hardened: Boolean
    ) {

        val index: Int
            get() = if (hardened) (srcIndex or 0x80000000.toInt()) else srcIndex
    }
}