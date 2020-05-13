package net.vizja.quadraticequation

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        toggleVisibility(visible = false)
        setXText()
        submit_btn.setOnClickListener {
            if (listOf(a_et.text, b_et.text, c_et.text).any { it.isBlank() }) {
                Toast.makeText(this, "All fields should be filled", LENGTH_LONG).show()
            } else {
                solution_lt.removeAllViews()
                val aCoefficient = convertToNumber(a_et.text)
                val bCoefficient = convertToNumber(b_et.text)
                val cCoefficient = convertToNumber(c_et.text)
                val quadraticEquation = let(aCoefficient, bCoefficient, cCoefficient) { a, b, c ->
                    QuadraticEquation(a, b, c)
                }
                val solution = quadraticEquation?.getSolution()
                solution?.let {
                    toggleVisibility(visible = true)
                    addTextView(it)
                }
            }
        }
    }

    private fun addTextView(solution: List<Spanned>) {
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        solution.forEach {
            val textView = TextView(this)
            textView.layoutParams = layoutParams
            textView.text = it
            solution_lt.addView(textView)
        }
    }

    private fun toggleVisibility(visible: Boolean) {
        if (visible) {
            solution_title_tv.visibility = VISIBLE
        } else {
            solution_title_tv.visibility = INVISIBLE
        }
    }

    private fun setXText() {
        val htmlStringWithMathSymbols =
            " X$POWER_TWO "
        x_2_tv.text = Html.fromHtml(htmlStringWithMathSymbols)
    }

    private fun convertToNumber(text: Editable): Double? {
        return try {
            text.toString().toDoubleOrNull()
        } catch (exception: Exception) {
            Timber.e(exception, "Failed to convert '$text' to number")
            throw exception
        }
    }

    private companion object {
        const val POWER_TWO = "&#178;"
    }
}
