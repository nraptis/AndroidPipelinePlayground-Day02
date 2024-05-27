package com.example.droidrenderdemoearth

import android.graphics.Bitmap
import android.opengl.GLES20
import java.lang.ref.WeakReference
import java.nio.FloatBuffer
import java.nio.IntBuffer


class Zippo(graphicsPipeline: GraphicsPipeline?,
            texture: GraphicsTexture?,
            graphics: GraphicsLibrary?) {

    val zippoVertz = arrayOf(
        VertexSprite2D(-512.0f, -512.0f, 0.0f, 0.0f),
        VertexSprite2D(512.0f, -512.0f, 1.0f, 0.0f),
        VertexSprite2D(-512.0f, 512.0f, 0.0f, 1.0f),
        VertexSprite2D(512.0f, 512.0f, 1.0f, 1.0f)
    )

    val indices = intArrayOf(0, 1, 2, 3)
    val indexBuffer: IntBuffer

    var color = Color(1.0f, 1.0f, 1.0f, 1.0f)

    var projectionMatrix = Matrix()
    var modelViewMatrix = Matrix()

    val graphics: GraphicsLibrary?
    val graphicsPipeline: GraphicsPipeline?
    val texture: GraphicsTexture?

    private var svn = 0.0f

    val vertexBuffer: GraphicsArrayBuffer<VertexSprite2D>

    init {

        this.graphics = graphics
        this.graphicsPipeline = graphicsPipeline
        this.texture = texture

        vertexBuffer = GraphicsArrayBuffer(graphics, zippoVertz)

        projectionMatrix.ortho(graphics?.widthf ?: 0.0f,
            graphics?.heightf ?: 0.0f)
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

        spin += 0.005f
        if (spin > (2.0f * piFloat)) {
            spin -= 2.0f * piFloat
        }

        color.blue = 0.5f + svn * 0.2f
        color.red = 0.5f + spin * 0.2f

        val width = graphics?.widthf ?: 0.0f
        val height = graphics?.heightf ?: 0.0f


        modelViewMatrix.translation(width / 4.0f, height / 4.0f, 0.0f)
        modelViewMatrix.rotateZ(spin)
        modelViewMatrix.scale(0.5f)

        graphics?.blendSetAlpha()

        graphics?.linkBufferToShaderProgram(graphicsPipeline?.programSprite2D, vertexBuffer)

        graphics?.uniformsTextureSet(graphicsPipeline?.programSprite2D, texture)
        graphics?.uniformsModulateColorSet(graphicsPipeline?.programSprite2D, color)
        graphics?.uniformsProjectionMatrixSet(graphicsPipeline?.programSprite2D, projectionMatrix)
        graphics?.uniformsModelViewMatrixSet(graphicsPipeline?.programSprite2D, modelViewMatrix)

        graphics?.drawTriangleStrips(indexBuffer, 4)
        graphics?.unlinkBufferFromShaderProgram (graphicsPipeline?.programSprite2D)
    }

}


