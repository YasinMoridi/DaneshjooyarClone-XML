package mr.moridi.videoplayer.presenter

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.core.content.ContextCompat
import mr.moridi.videoplayer.R
import mr.moridi.videoplayer.ext.InformationView


class InformationPresenter(private val view: InformationView, private val context: Context) {

    fun setupTextShaders(textSizeTeacher: Float, textSizeStudent: Float, textSizeClock: Float, textSizeUniversity: Float) {
        val colors = intArrayOf(
            ContextCompat.getColor(context, R.color.blueSelectVideo),
            ContextCompat.getColor(context, R.color.endColorGradient)
        )

        val textShaderTeacher = LinearGradient(
            0f, 0f, 0f, textSizeTeacher,
            colors, null, Shader.TileMode.CLAMP
        )
        view.applyTextShaderTeacher(textShaderTeacher)

        val textShaderStudent = LinearGradient(
            0f, 0f, 0f, textSizeStudent,
            colors, null, Shader.TileMode.CLAMP
        )
        view.applyTextShaderStudent(textShaderStudent)

        val textShaderClock = LinearGradient(
            0f, 0f, 0f, textSizeClock,
            colors, null, Shader.TileMode.CLAMP
        )
        view.applyTextShaderClock(textShaderClock)

        val textShaderUniversity = LinearGradient(
            0f, 0f, 0f, textSizeUniversity,
            colors, null, Shader.TileMode.CLAMP
        )
        view.applyTextShaderUniversity(textShaderUniversity)
    }
}
