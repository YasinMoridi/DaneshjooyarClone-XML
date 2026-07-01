package mr.moridi.videoplayer.ext

import android.graphics.Shader

interface InformationView {
    fun applyTextShaderTeacher(shader: Shader)
    fun applyTextShaderStudent(shader: Shader)
    fun applyTextShaderClock(shader: Shader)
    fun applyTextShaderUniversity(shader: Shader)
}
