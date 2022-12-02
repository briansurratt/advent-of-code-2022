package dev.surratt.aoc

data class Elf (val number : Int, val calories : Int)

fun buildListOfElves(payload : String) : List<Elf> {

    val lines = payload.lines().toMutableList()

    println("number of lines = ${lines.size}")

    val elves = mutableListOf<Elf>()

    val caloriesList = lines.map {
        val s = it.trim()
        s.ifEmpty {
            "-1"
        }
    }.map { it.toInt() }

    var currentElf = 1
    var calorieAccumulator = 0
    for (calorieValue in caloriesList) {
        if (calorieValue == -1) {
            elves.add(Elf(currentElf, calorieAccumulator))
            calorieAccumulator = 0
            currentElf++
        } else {
            calorieAccumulator += calorieValue
        }
    }

    if (calorieAccumulator > 0) {
        elves.add(Elf(currentElf, calorieAccumulator))
    }

    println("number of elves = ${elves.size}")

    elves.sortByDescending { elf -> elf.calories }

    for (elf in elves) {
        println(elf)
    }

    return elves
}

fun caloriesCarriedByTop3(payload : String) : Int {
    return buildListOfElves(payload).subList(0,3).sumBy { elf -> elf.calories }
}

fun findElfWIthMostCalories(payload : String) : Elf {

    val elves = buildListOfElves(payload)

    return elves.first()

}