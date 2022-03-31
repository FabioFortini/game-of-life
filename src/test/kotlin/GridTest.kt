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
    internal fun `grid should return next generation without changes`() {
        val grid = Grid()

        var newGrid = grid.next()

        assertEquals(grid, newGrid)
    }
}