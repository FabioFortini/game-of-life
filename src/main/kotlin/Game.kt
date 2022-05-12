import java.io.File
import java.util.*

fun main(args: Array<String>) {
    var ran = Random()
    var alives = mutableListOf<Pair<Int, Int>>()
    for (rowIndex in 0..49) {
        for (colIndex in 0..49)  {
            if(ran.nextBoolean()) {
                alives.add(Pair(rowIndex, colIndex))
            }
        }
    }
    var grid = Grid(50, 50, alives)

    while(true) {
        File("out.html").writeText(grid.toString())
        grid = grid.next()
        Thread.sleep(500)
    }
}
