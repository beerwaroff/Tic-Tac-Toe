package tictactoe

import kotlin.math.abs

fun threeInARow(x: Char, y: MutableList<MutableList<Char>>): Boolean {
    var result = false

    for (i in y.indices)
        if (y[i][0] == x && y[i][0] != '_')
            if (y[i][0] == y[i][1] && y[i][0] == y[i][2])
                result = true

    val checkField = mutableListOf<MutableList<Char>>()

    for (i in y.indices)
        checkField.add(mutableListOf(y[0][i], y[1][i], y[2][i]))

    for (i in checkField.indices)
        if (checkField[i][0] == x && checkField[i][0] != '_')
            if (checkField[i][0] == checkField[i][1] && checkField[i][0] == checkField[i][2])
                result = true

    if (y[1][1] == x && y[1][1] != '_')
        if ((y[0][0] == y[1][1] && y[0][0] == y[2][2]) || (y[0][2] == y[1][1] && y[2][0] == y[1][1]))
            result = true

    return result
}

fun printGrid(field: MutableList<MutableList<Char>>) {
    println("---------")
    for (i in field.indices)
        println("| ${field[i].joinToString(" ")} |")
    println("---------")
}

fun main() {
    val field = mutableListOf<MutableList<Char>>()

    for (i in 1..3)
        field.add(mutableListOf('_', '_', '_'))

    printGrid(field)

    var who = 0
    loop@ while ('_' in field[0] || '_' in field[1] || '_' in field[2]) {

        var notify: Boolean

        do {
            val (x, y) = readln().split(" ")

            try {
                if (field[x.toInt()-1][y.toInt()-1] != '_') {
                    notify = false
                    println("This cell is occupied! Choose another one!")
                } else {
                    if (who == 0) {
                        field[x.toInt() - 1][y.toInt() - 1] = 'X'
                        notify = true
                        who ++
                    } else {
                        field[x.toInt() - 1][y.toInt() - 1] = 'O'
                        notify = true
                        who --
                    }
                }

            } catch (e: NumberFormatException) {
                notify = false
                println("You should enter numbers!")
            } catch (e: IndexOutOfBoundsException) {
                notify = false
                println("Coordinates should be from 1 to 3!")
            }
        } while (!notify)

        var conclusion = ""

        for (i in field.indices)
            if ('_' in field[i]) {
                conclusion = "Game not finished"
                break
            }

        printGrid(field)

        if (!threeInARow('O', field) && !threeInARow('X', field) && conclusion != "Game not finished") {
            println("Draw")
            break@loop
        }
        if (!threeInARow('O', field) && threeInARow('X', field)) {
            println("X wins")
            break@loop
        }
        if (threeInARow('O', field) && !threeInARow('X', field)) {
            println("O wins")
            break@loop
        }
    }
}