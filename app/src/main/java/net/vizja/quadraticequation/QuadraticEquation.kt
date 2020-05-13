package net.vizja.quadraticequation

import android.text.Html
import android.text.Spanned
import kotlin.math.pow
import kotlin.math.sqrt


data class QuadraticEquation(val a: Double, val b: Double, val c: Double) {

    fun getSolution(): List<Spanned> {
        val squaredD = b.pow(2.0) - (4 * a * c)
        var solutionText = """
            1. Find discriminant according to formula:
            D = b$POWER_TWO - 4ac
            D = ($b)$POWER_TWO - 4 * ($a) * ($c)
            D = $squaredD
        """.trimMargin()
        val text = if (squaredD >= 0) {
            solutionText = """
                $solutionText
                2. D $GREATER_OR_EQUAL 0, then there is solution which is real number
            """.trimIndent()
            findRealSolution(squaredD, solutionText)
        } else {
            """
                $solutionText
                2. As D < 0, then there is no solution in scope of real numbers
            """.trimIndent()
        }
        return text.lines().map { Html.fromHtml(it) }
    }

    private fun findRealSolution(squaredD: Double, solutionText: String): String {
        return if (squaredD == 0.0) {
            findSingleRealSolution(solutionText)
        } else {
            findTwoRealSolutions(squaredD, solutionText)
        }
    }

    private fun findSingleRealSolution(solutionText: String): String {
        val x = (-b / (2.0 * a)).round(2)
        return """
                $solutionText
                3. D = 0, then there is single real number solution which is calculated with:
                x = - b / 2a
                x = - ($b) / 2 * ($a)
                x = $x
            """.trimIndent()
    }

    private fun findTwoRealSolutions(squaredD: Double, solutionText: String): String {
        val x_1 = (-(b + sqrt(squaredD)) / (2.0 * a)).round(2)
        val x_2 = (-(b - sqrt(squaredD)) / (2.0 * a)).round(2)
        return """
                $solutionText
                3. D > 0, then there are two real number solutions which are calculated with:
                x = (- b $PLUS_MINUS ${SQUARE_ROOT}D) / 2a
                x_1 = ( - ($b) + $SQUARE_ROOT$squaredD) / 2 * ($a)
                x_1 = $x_1
                x_2 = ( - ($b) - $SQUARE_ROOT$squaredD) / 2 * ($a)
                x_2 = $x_2
            """.trimIndent()
    }

    private companion object {
        const val PLUS_MINUS = "&#177;"
        const val GREATER_OR_EQUAL = "&#8805;"
        const val POWER_TWO = "&#178;"
        const val SQUARE_ROOT = "&#8730;"
    }
}
