package com.example.droidrenderdemoearth

import java.nio.IntBuffer

class Yodel(graphicsPipeline: GraphicsPipeline?,
            graphics: GraphicsLibrary?) {

    val yodelleVertz = arrayOf(
        VertexShape2D(-512.0f, -512.0f),
        VertexShape2D(512.0f, -512.0f),
        VertexShape2D(-512.0f, 512.0f),
        VertexShape2D(512.0f, 512.0f)
    )

    val indices = intArrayOf(0, 1, 2, 3)
    val indexBuffer: IntBuffer

    var color = Color(1.0f, 1.0f, 1.0f, 0.5f)

    var projectionMatrix = Matrix()
    var modelViewMatrix = Matrix()

    val graphics: GraphicsLibrary?
    val graphicsPipeline: GraphicsPipeline?

    private var svn = 0.0f

    val vertexBuffer: GraphicsArrayBuffer<VertexShape2D>

    init {

        this.graphics = graphics
        this.graphicsPipeline = graphicsPipeline


        vertexBuffer = GraphicsArrayBuffer(graphics, yodelleVertz)

        projectionMatrix.ortho(graphics?.widthf ?: 0.0f,
            graphics?.heightf ?: 0.0f)
        indexBuffer = graphics?.indexBufferGenerate(indices) ?: IntBuffer.allocate(0)
    }

    var spin = 0.0f
    fun draw() {

        // Add program to OpenGL ES environment
        val piFloat: Float = kotlin.math.PI.toFloat()

        svn += 0.0125f
        if (svn > (2.0f * piFloat)) {
            svn -= 2.0f * piFloat
        }

        spin += 0.0035f
        if (spin > (2.0f * piFloat)) {
            spin -= 2.0f * piFloat
        }

        color.green = 0.5f + svn * 0.2f
        color.blue = 0.5f + spin * 0.2f


        val width = graphics?.widthf ?: 0.0f
        val height = graphics?.heightf ?: 0.0f


        modelViewMatrix.translation(width * 3.0f / 4.0f, height / 4.0f, 0.0f)
        modelViewMatrix.rotateZ(spin)
        modelViewMatrix.scale(0.5f)


        graphics?.blendSetAlpha()

        graphics?.linkBufferToShaderProgram(graphicsPipeline?.programShape2D, vertexBuffer)

        graphics?.uniformsModulateColorSet(graphicsPipeline?.programShape2D, color)
        graphics?.uniformsProjectionMatrixSet(graphicsPipeline?.programShape2D, projectionMatrix)
        graphics?.uniformsModelViewMatrixSet(graphicsPipeline?.programShape2D, modelViewMatrix)

        graphics?.drawTriangleStrips(indexBuffer, 4)
        graphics?.unlinkBufferFromShaderProgram (graphicsPipeline?.programShape2D)
    }

}


