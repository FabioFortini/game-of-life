import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GridTest {
    @Test
    internal fun `grid should be 10x10`() {
        val grid = Grid()

        assertEquals(10, grid.rows)
        assertEquals(10, grid.cols)
    }

    @Test
    internal fun `empty grid should return next generation without changes`() {
        val grid = Grid()
        val newGrid = grid.next()

        assertEquals(grid, newGrid)
    }

    @Test
    internal fun `xxx`() {
        val grid = Grid(rows = 2, cols = 2, aliveCells = listOf(Pair(0,0), Pair(1, 1)))
        val newGrid = grid.next()

        assertEquals(grid, newGrid)
    }


    @Test
    fun `initialize grid with different starting status cells`() {
        val grid = Grid(rows = 2, cols = 2, aliveCells = listOf(Pair(0,0), Pair(1, 1)))

        assertEquals(1, grid.getCell(0, 0).status)
        assertEquals(1, grid.getCell(1, 1).status)
        assertEquals(0, grid.getCell(0, 1).status)
        assertEquals(0, grid.getCell(1, 0).status)
    }
}