import com.eskelinenantti.resources.readResource
import kotlin.math.abs

data class Coordinate(val x: Int, val y: Int) {
    fun isNextTo(coordinate: Coordinate) =
        (abs(this.x - coordinate.x) <= 1 && abs(this.y - coordinate.y) <= 1)

}

data class NumberLocation(val number: Int, val location: Collection<Coordinate>)

val inputData = readResource("/inputs/3.txt")
val numberLocations = inputData.split("\n").withIndex().map {
    val yCoord = it.index
    Regex("""\d+""").findAll(it.value).map { match ->
        NumberLocation(match.value.toInt(), (match.range.start..match.range.endInclusive).map { xCoord ->
            Coordinate(xCoord, yCoord)
        })
    }.toList()
}.flatten()

val gearLocations = inputData.split("\n").withIndex().map {
    val yCoord = it.index
    it.value.withIndex().filter { char ->
        char.value == '*'
    }.map { Coordinate(it.index, yCoord) }.toList()
}.flatten()

val sum = gearLocations.map { gearLocation ->
    numberLocations.filter { number ->
        number.location.any { numberLocation -> numberLocation.isNextTo(gearLocation) }
    }
}.filter { it.size == 2 }.sumOf { it.first().number * it.last().number }
sum