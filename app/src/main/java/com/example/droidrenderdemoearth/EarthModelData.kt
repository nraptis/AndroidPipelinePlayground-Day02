package com.example.droidrenderdemoearth

import kotlin.math.min
import kotlin.math.PI

class EarthModelData(width: Float, height: Float) {

    companion object {
        const val tileCountV = 8
        const val tileCountH = 8
    }

    val points: Array<Array<Float3>>
    val normals: Array<Array<Float3>>
    val textureCoords: Array<Array<Float2>>
    init {
        val _points = Array(tileCountH + 1) { Array(tileCountV + 1) { Float3(0.0f, 0.0f, 11.0f) } }
        val _normals = Array(tileCountH + 1) { Array(tileCountV + 1) { Float3(0.0f, 0.0f, 11.0f) } }
        val _textureCoords = Array(tileCountH + 1) { Array(tileCountV + 1) { Float2(0.0f, 11.0f) } }
        val radius: Float = min(width, height) * (0.5f * 0.75f)
        val startRotationH = PI.toFloat()
        val endRotationH = startRotationH + PI.toFloat() * 2.0f
        val startRotationV = PI.toFloat()
        val endRotationV = 0.0f
        var indexV = 0
        while (indexV <= tileCountV) {
            val percentV = indexV.toFloat() / tileCountV.toFloat()
            val _angleV = startRotationV + (endRotationV - startRotationV) * percentV
            var indexH = 0
            while (indexH <= tileCountH) {
                val percentH = indexH.toFloat() / tileCountH.toFloat()
                val _angleH = startRotationH + (endRotationH - startRotationH) * percentH
                var point = Float3(0.0f, 1.0f, 0.0f)
                point = Math.rotateX(point, _angleV)
                point = Math.rotateY(point, _angleH)
                _points[indexH][indexV].x = point.x * radius
                _points[indexH][indexV].y = point.y * radius
                _points[indexH][indexV].z = point.z * radius

                _normals[indexH][indexV].x = point.x
                _normals[indexH][indexV].y = point.y
                _normals[indexH][indexV].z = point.z

                _textureCoords[indexH][indexV].x = percentH
                _textureCoords[indexH][indexV].y = percentV

                indexH++
            }
            indexV++
        }
        points = _points
        normals = _normals
        textureCoords = _textureCoords
    }
}