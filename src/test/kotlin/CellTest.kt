import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CellTest {
    @Test
    internal fun `cell should start as dead`() {
        val cell = Cell()

        assertEquals(0, cell.status)
    }
}