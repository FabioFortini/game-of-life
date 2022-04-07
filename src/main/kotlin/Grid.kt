
class Grid(val rows: Int = 10, val cols: Int = 10) {

    val matrix = Array(rows) { Array(cols) { Cell() } }

    fun next(): Grid {
        return Grid()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Grid) return false

        if (!matrix.contentDeepEquals(other.matrix)) return false

        return true
    }

    override fun hashCode(): Int {
        return matrix.contentDeepHashCode()
    }


}