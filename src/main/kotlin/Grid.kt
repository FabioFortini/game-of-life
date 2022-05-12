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

    private fun calculateNewStatusCell(row: Int, col: Int, status: Int): Int {
        val aliveNeighbours = getNeighboursAlive(row, col)
        if(aliveNeighbours < 2)
            return 0
        if(aliveNeighbours > 3)
            return 0
        if(status == 0 && aliveNeighbours == 3)
            return 1
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

    fun getStatusCell(row: Int, col: Int): Int {
        if (row < 0 || col < 0)
            return 0
        if (col > cols - 1)
            return 0
        if (row > rows - 1)
            return 0
        return getCell(row, col).status
    }

    fun getNeighboursAlive(row: Int, col: Int): Int {
        return getStatusCell(row - 1, col - 1) +
                getStatusCell(row - 1, col) +
                getStatusCell(row - 1, col + 1) +
                getStatusCell(row, col - 1) +
                getStatusCell(row, col + 1) +
                getStatusCell(row + 1, col - 1) +
                getStatusCell(row + 1, col) +
                getStatusCell(row + 1, col + 1)
    }
}