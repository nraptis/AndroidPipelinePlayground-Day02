package com.example.droidrenderdemoearth

import android.content.Context
import android.opengl.GLES20
import java.lang.ref.WeakReference

class GraphicsPipeline(context: Context) {

    private val contextRef: WeakReference<Context> = WeakReference(context)
    val context: Context?
        get() = contextRef.get()




    val functionSprite2DVertex: Int
    val functionSprite2DFragment: Int
    var programSprite2D: ShaderProgramSprite2D

    val functionShape2DVertex: Int
    val functionShape2DFragment: Int
    var programShape2D: ShaderProgramShape2D

    val functionSprite3DVertex: Int
    val functionSprite3DFragment: Int
    var programSprite3D: ShaderProgramSprite3D

    val functionShape3DVertex: Int
    val functionShape3DFragment: Int
    var programShape3D: ShaderProgramShape3D

    /*
    shape_2d_fragment.glsl
    shape_2d_vertex.glsl
    shape_3d_fragment.glsl
    shape_3d_vertex.glsl

     */


    init {

        functionSprite2DVertex = loadShaderVertex("sprite_2d_vertex.glsl")
        functionSprite2DFragment = loadShaderFragment("sprite_2d_fragment.glsl")
        programSprite2D = ShaderProgramSprite2D("sprite_2d", functionSprite2DVertex, functionSprite2DFragment)

        functionShape2DVertex = loadShaderVertex("shape_2d_vertex.glsl")
        functionShape2DFragment = loadShaderFragment("shape_2d_fragment.glsl")
        programShape2D = ShaderProgramShape2D("shape_2d", functionShape2DVertex, functionShape2DFragment)


        functionSprite3DVertex = loadShaderVertex("sprite_3d_vertex.glsl")
        functionSprite3DFragment = loadShaderFragment("sprite_3d_fragment.glsl")
        programSprite3D = ShaderProgramSprite3D("sprite_3d", functionSprite3DVertex, functionSprite3DFragment)

        functionShape3DVertex = loadShaderVertex("shape_3d_vertex.glsl")
        functionShape3DFragment = loadShaderFragment("shape_3d_fragment.glsl")
        programShape3D = ShaderProgramShape3D("shape_3d", functionShape3DVertex, functionShape3DFragment)


    }

    private fun loadProgram(vertexShader: Int, fragmentShader: Int): Int {
        return GLES20.glCreateProgram().also { program ->
            GLES20.glAttachShader(program, vertexShader)
            GLES20.glAttachShader(program, fragmentShader)
            GLES20.glLinkProgram(program)
        }
    }

    private fun loadShaderVertex(fileName: String): Int {
        return loadShader(GLES20.GL_VERTEX_SHADER, fileName)
    }

    private fun loadShaderFragment(fileName: String): Int {
        return loadShader(GLES20.GL_FRAGMENT_SHADER, fileName)
    }

    private fun loadShader(type: Int, fileName: String): Int {
        context?.let {
            FileUtils.readFileFromAssetAsString(it, fileName)?.let { fileContent ->
                return GLES20.glCreateShader(type).also { shader ->
                    GLES20.glShaderSource(shader, fileContent)
                    GLES20.glCompileShader(shader)
                }
            }
        }
        return 0
    }
}