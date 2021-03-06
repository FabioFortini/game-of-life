import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GridTest {
    @Test
    fun `grid should be 10x10`() {
        val grid = Grid()

        assertEquals(10, grid.rows)
        assertEquals(10, grid.cols)
    }

    @Test
    fun `initialize grid with different starting status cells`() {
        val grid = Grid(rows = 2, cols = 2, aliveCells = listOf(Pair(0,0), Pair(1, 1)))

        assertEquals(1, grid.getCell(0, 0).status)
        assertEquals(1, grid.getCell(1, 1).status)
        assertEquals(0, grid.getCell(0, 1).status)
        assertEquals(0, grid.getCell(1, 0).status)
    }

    @Test
    fun `Any live cell with fewer than two live neighbours dies, as if by underpopulation`() {
        val grid = Grid(rows = 3, cols = 3, aliveCells = listOf(Pair(0,0), Pair(0,1)))

        val newGrid = grid.next()

        assertEquals(0, newGrid.getCell(0, 0).status)
        assertEquals(0, newGrid.getCell(0, 1).status)
    }

    @Test
    fun `Any live cell with two live neighbours lives on to the next generation`() {
        val grid = Grid(rows = 3, cols = 3, aliveCells = listOf(Pair(0,0), Pair(1, 0), Pair(0,1)))

        val newGrid = grid.next()

        assertEquals(1, newGrid.getCell(0, 0).status)
        assertEquals(1, newGrid.getCell(1, 0).status)
        assertEquals(1, newGrid.getCell(0, 1).status)
    }

    @Test
    fun `Any live cell with three live neighbours lives on to the next generation`() {
        val grid = Grid(rows = 3, cols = 3, aliveCells = listOf(Pair(0,0), Pair(1, 0), Pair(0,1), Pair(1,1)))

        val newGrid = grid.next()

        assertEquals(1, newGrid.getCell(0, 0).status)
        assertEquals(1, newGrid.getCell(1, 0).status)
        assertEquals(1, newGrid.getCell(0, 1).status)
        assertEquals(1, newGrid.getCell(1, 1).status)
    }

    @Test
    fun `Any live cell with more than three live neighbours dies, as if by overpopulation`() {
        val grid = Grid(rows = 3, cols = 3, aliveCells = listOf(Pair(1,1), Pair(0,1), Pair(1,2), Pair(2,1), Pair(1,0)))

        val newGrid = grid.next()

        assertEquals(0, newGrid.getCell(1, 1).status)
    }

    @Test
    fun `Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction`() {
        val grid = Grid(rows = 3, cols = 3, aliveCells = listOf(Pair(0,1), Pair(1,0), Pair(1,1)))

        val newGrid = grid.next()

        assertEquals(1, newGrid.getCell(0, 0).status)
    }

    @Test
    fun `get how many neighbours alive 0`() {
        val grid = Grid(rows = 3, cols = 3)
        val aliveNeighbours = grid.getNeighboursAlive(1,1)

        assertEquals(0, aliveNeighbours)
    }

    @Test
    fun `find alive cell top left`() {
        val grid = Grid(rows = 3, cols = 3, listOf(Pair(0,0)))
        val aliveNeighbours = grid.getNeighboursAlive(1,1)

        assertEquals(1, aliveNeighbours)
    }

    @Test
    fun `find alive cell top center`() {
        val grid = Grid(rows = 3, cols = 3, listOf(Pair(0,1)))
        val aliveNeighbours = grid.getNeighboursAlive(1,1)

        assertEquals(1, aliveNeighbours)
    }

    @Test
    fun `find alive cell top right`() {
        val grid = Grid(rows = 3, cols = 3, listOf(Pair(0,2)))
        val aliveNeighbours = grid.getNeighboursAlive(1,1)

        assertEquals(1, aliveNeighbours)
    }

    @Test
    fun `find alive cell left`() {
        val grid = Grid(rows = 3, cols = 3, listOf(Pair(1,0)))
        val aliveNeighbours = grid.getNeighboursAlive(1,1)

        assertEquals(1, aliveNeighbours)
    }

    @Test
    fun `find alive cell right`() {
        val grid = Grid(rows = 3, cols = 3, listOf(Pair(1,2)))
        val aliveNeighbours = grid.getNeighboursAlive(1,1)

        assertEquals(1, aliveNeighbours)
    }

    @Test
    fun `find alive cell bottom left`() {
        val grid = Grid(rows = 3, cols = 3, listOf(Pair(2,0)))
        val aliveNeighbours = grid.getNeighboursAlive(1,1)

        assertEquals(1, aliveNeighbours)
    }

    @Test
    fun `find alive cell bottom center`() {
        val grid = Grid(rows = 3, cols = 3, listOf(Pair(2,1)))
        val aliveNeighbours = grid.getNeighboursAlive(1,1)

        assertEquals(1, aliveNeighbours)
    }

    @Test
    fun `find alive cell bottom right`() {
        val grid = Grid(rows = 3, cols = 3, listOf(Pair(2,2)))
        val aliveNeighbours = grid.getNeighboursAlive(1,1)

        assertEquals(1, aliveNeighbours)
    }

    @Test
    fun `works calculating neighbours starting from top left corner`() {
        val grid = Grid(rows = 3, cols = 3)
        grid.getNeighboursAlive(0,0)
    }

    @Test
    fun `works calculating neighbours starting from top right corner`() {
        val grid = Grid(rows = 3, cols = 3)
        grid.getNeighboursAlive(0,2)
    }

    @Test
    fun `works calculating neighbours starting from bottom left corner`() {
        val grid = Grid(rows = 3, cols = 3)
        grid.getNeighboursAlive(2,0)
    }

    @Test
    fun `works calculating neighbours starting from bottom right corner`() {
        val grid = Grid(rows = 3, cols = 3)
        grid.getNeighboursAlive(2,2)
    }

    @Test
    fun `grid should represent itself as a string`() {
        val grid = Grid(rows = 2, cols = 2, aliveCells = listOf(Pair(0,0), Pair(1, 1)))

        val gridRepresentation = grid.toString()

        assertEquals("*-\n-*\n", gridRepresentation)
    }
}