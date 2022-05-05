class Grid(val rows: Int = 10, val cols: Int = 10, aliveCells: List<Pair<Int, Int>> = emptyList()) {

    val matrix = Array(rows) { Array(cols) { Cell() } }

    init {
        aliveCells.forEach { (x, y) -> matrix[x][y].status = 1 }
    }

    fun next(): Grid {
        val grid = Grid(rows, cols)

        matrix.forEachIndexed { x, row ->
            row.forEachIndexed { y, cell ->
                val newStatus = calculateNewStatusCell(x, y, cell.status)
                grid.setStatusCell(x, y, newStatus)
            }
        }

        return grid
    }

    private fun calculateNewStatusCell(x: Int, y: Int, status: Int): Int {
        return status
    }

    private fun setStatusCell(x: Int, y: Int, status: Int) {
        getCell(x, y).status = status
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

    fun getCell(rowIndex: Int, colIndex: Int): Cell {
        return matrix[rowIndex][colIndex]
    }

    override fun toString(): String {
        var string = ""

        matrix.forEachIndexed { _, row ->
            row.forEachIndexed { _, cell ->
                string += if (cell.status == 0) "-" else "*"
            }

            string += "\n"
        }

        return string
    }

    fun getNeighboursAlive(row: Int, col: Int): Int {
        return getCell(row - 1, col - 1).status +
                getCell(row - 1, col).status +
                getCell(row - 1, col + 1).status
//        return getCell(x - 1, y - 1).status +
//                getCell(x, y - 1).status +
//                getCell(x + 1, y - 1).status
//        +
//                getCell(x - 1, y).status +
//                getCell(x + 1, y).status +
//                getCell(x - 1, y + 1).status +
//                getCell(x, y + 1).status +
//                getCell(x + 1, y + 1).status
    }


}