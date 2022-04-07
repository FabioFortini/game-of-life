class Cell {
    val status: Int = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cell) return false

        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        return status
    }


}