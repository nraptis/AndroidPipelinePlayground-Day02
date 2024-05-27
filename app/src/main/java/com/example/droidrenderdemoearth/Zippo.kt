package com.example.droidrenderdemoearth

import android.graphics.Bitmap
import android.opengl.GLES20
import java.lang.ref.WeakReference
import java.nio.FloatBuffer
import java.nio.IntBuffer


val zippoVertz = arrayOf(
    VertexSprite2D(-512.0f, -512.0f, 0.0f, 0.0f),
    VertexSprite2D(512.0f, -512.0f, 1.0f, 0.0f),
    VertexSprite2D(-512.0f, 512.0f, 0.0f, 1.0f),
    VertexSprite2D(512.0f, 512.0f, 1.0f, 1.0f)

)

/*
val zippoVertz = arrayOf(
    VertexSprite2D(-0.5f + 0.2f, -0.5f - 0.3f, 0.0f, 0.0f),
    VertexSprite2D(0.5f + 0.2f, -0.5f - 0.3f, 1.0f, 0.0f),
    VertexSprite2D(-0.5f + 0.2f, 0.5f - 0.3f, 0.0f, 1.0f),
    VertexSprite2D(0.5f + 0.2f, -0.5f - 0.3f, 1.0f, 0.0f),
    VertexSprite2D(-0.5f + 0.2f, 0.5f - 0.3f, 0.0f, 1.0f),
    VertexSprite2D(0.5f + 0.2f, 0.5f - 0.3f, 1.0f, 1.0f)
)
 */

class Zippo(graphicsPipeline: GraphicsPipeline,
            bitmap: Bitmap?,
                graphics: GraphicsLibrary?) {

    val indices = intArrayOf(0, 1, 2, 3)
    val indexBuffer: IntBuffer



    var color = Color(1.0f, 0.5f, 0.75f, 0.5f)
    var colorBuffer: FloatBuffer

    var projectionMatrix = Matrix()
    var projectionMatrixBuffer: FloatBuffer


    var modelViewMatrix = Matrix()
    var modelViewMatrixBuffer: FloatBuffer


    private val bitmapRef: WeakReference<Bitmap> = WeakReference(bitmap)
    val bitmap: Bitmap?
        get() = bitmapRef.get()

    private val graphicsRef: WeakReference<GraphicsLibrary> = WeakReference(graphics)
    val graphics: GraphicsLibrary?
        get() = graphicsRef.get()


    private val graphicsPipelineRef: WeakReference<GraphicsPipeline> = WeakReference(graphicsPipeline)
    val graphicsPipeline: GraphicsPipeline?
        get() = graphicsPipelineRef.get()


    private var textureSlot = 0

    private var svn = 0.0f

    val gabbo: GraphicsArrayBuffer<VertexSprite2D>

    //val vertexBuffer: FloatBuffer
    //var bufferIndex: Int


    init {
        graphics?.let { _gfx ->
            textureSlot = _gfx.textureGenerate(bitmap)
        }
        println("textureSlot = " + textureSlot)

        gabbo = GraphicsArrayBuffer(graphics, zippoVertz)


        projectionMatrix.ortho(1080.0f, 2154.0f)

        colorBuffer = graphics?.floatBufferGenerate(color) ?: FloatBuffer.allocate(0)
        projectionMatrixBuffer = graphics?.floatBufferGenerate(projectionMatrix) ?: FloatBuffer.allocate(0)

        for (i in 0 until projectionMatrixBuffer.limit()) {
            println("index: $i = ${projectionMatrixBuffer[i]}")
        }

        modelViewMatrixBuffer = graphics?.floatBufferGenerate(modelViewMatrix) ?: FloatBuffer.allocate(0)

        indexBuffer = graphics?.indexBufferGenerate(indices) ?: IntBuffer.allocate(0)

    }

    var spin = 0.0f
    fun draw() {
        // Add program to OpenGL ES environment
        val piFloat: Float = kotlin.math.PI.toFloat()

        svn += 0.01f
        if (svn > (2.0f * piFloat)) {
            svn -= 2.0f * piFloat
        }

        spin += 0.05f
        if (spin > (2.0f * piFloat)) {
            spin -= 2.0f * piFloat
        }

        val sineValue: Float = kotlin.math.sin(svn.toDouble()).toFloat()



        color.blue = 0.5f + svn * 0.2f
        color.red = 0.5f + spin * 0.2f

        graphics?.floatBufferWrite(color, colorBuffer)

        //projectionMatrix.ortho(1080.0f, 2154.0f)


        modelViewMatrix.translation(1080.0f / 2.0f, 2154.0f / 2.0f, 0.0f)

        modelViewMatrix.rotateZ(spin)

        //modelViewMatrix.scale(kotlin.math.sin(spin) * 0.25f + 1.0f)


        graphics?.floatBufferWrite(modelViewMatrix, modelViewMatrixBuffer)


        graphics?.linkBufferToShaderProgram(graphicsPipeline?.programSprite2D, gabbo)


        //GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureSlot)
        graphics?.textureBind(textureSlot)

        val program = graphicsPipeline!!.programSprite2D

        GLES20.glUniform1i(program.uniformLocationTexture, 0)


        //graphics?.uniformsModulateColorSet(graphicsPipeline?.programSprite2D, color)

        graphics?.uniformsModulateColorSet(graphicsPipeline?.programSprite2D, colorBuffer)

        //GLES20.glUniform4f(program.uniformLocationModulateColor, color.red, color.green, color.blue, color.alpha)
        //GLES20.glUniform4fv(program.uniformLocationModulateColor, 1, colorBuffer)


        //graphics?.uniformsProjectionMatrixSet(graphicsPipeline?.programSprite2D, projectionMatrixBuffer)

        graphics?.uniformsProjectionMatrixSet(graphicsPipeline?.programSprite2D, projectionMatrix)

        graphics?.uniformsModelViewMatrixSet(graphicsPipeline?.programSprite2D, modelViewMatrixBuffer)

        graphics?.blendSetAlpha()

        graphics?.drawTriangleStrips(indexBuffer, 4)

        graphics?.unlinkBufferFromShaderProgram (graphicsPipeline?.programSprite2D)

    }



}


