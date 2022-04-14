
class Grid(val rows: Int = 10, val cols: Int = 10, aliveCells: List<Pair<Int, Int>> = emptyList()) {

    val matrix =  Array(rows) { Array(cols) { Cell() } }

    init {
        aliveCells.forEach { (x, y) -> matrix[x][y].status = 1 }
    }

    fun next(): Grid {
       val grid = Grid(rows,cols)
        matrix.forEachIndexed{index, row->
            row.forEachIndexed{y,cell ->
                grid.setStatusCell(index, y, cell.status)
            }
        }
        return grid
    }

    private fun setStatusCell(x: Int, y: Int, status: Int) {
        getCell(x,y).status = status
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



}