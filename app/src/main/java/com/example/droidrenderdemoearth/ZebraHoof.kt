package com.example.droidrenderdemoearth

import java.nio.IntBuffer

class ZebraHoof(graphicsPipeline: GraphicsPipeline?,
                graphics: GraphicsLibrary?,
                var earth: Earth) {

    val zebbroVertz = arrayOf(
        VertexShape3D(-512.0f, -512.0f, 0.0f),
        VertexShape3D(512.0f, -512.0f, 0.0f),
        VertexShape3D(-512.0f, 512.0f, 100.0f),
        VertexShape3D(512.0f, 512.0f, 100.0f)
    )

    val indices = intArrayOf(0, 1, 2, 3)
    val indexBuffer: IntBuffer

    var color = Color(1.0f, 1.0f, 1.0f, 0.5f)

    var projectionMatrix = Matrix()
    var modelViewMatrix = Matrix()

    val graphics: GraphicsLibrary?
    val graphicsPipeline: GraphicsPipeline?

    private var svn = 0.0f

    val vertexBuffer: GraphicsArrayBuffer<VertexShape3D>

    init {

        this.graphics = graphics
        this.graphicsPipeline = graphicsPipeline


        vertexBuffer = GraphicsArrayBuffer(graphics, zebbroVertz)

        projectionMatrix.ortho(graphics?.widthf ?: 0.0f,
            graphics?.heightf ?: 0.0f)
        indexBuffer = graphics?.indexBufferGenerate(indices) ?: IntBuffer.allocate(0)
    }

    var spin = 0.0f
    fun draw() {

        // Add program to OpenGL ES environment
        val piFloat: Float = kotlin.math.PI.toFloat()

        svn += 0.0042f
        if (svn > (2.0f * piFloat)) {
            svn -= 2.0f * piFloat
        }

        spin += 0.0028f
        if (spin > (2.0f * piFloat)) {
            spin -= 2.0f * piFloat
        }

        color.blue = 0.5f + svn * 0.2f
        color.red = 0.5f + spin * 0.2f

        val width = graphics?.widthf ?: 0.0f
        val height = graphics?.heightf ?: 0.0f


        modelViewMatrix.translation(width / 4.0f, height * 3.0f / 4.0f, 0.0f)
        modelViewMatrix.rotateY(spin)
        modelViewMatrix.scale(0.5f)

        for (strip in earth.earthModelDataStrips) {

            graphics?.blendSetAlpha()

            graphics?.linkBufferToShaderProgram(graphicsPipeline?.programShape3D, strip.shapeVertexBuffer)

            graphics?.uniformsModulateColorSet(graphicsPipeline?.programShape3D, color)
            graphics?.uniformsProjectionMatrixSet(graphicsPipeline?.programShape3D, projectionMatrix)
            graphics?.uniformsModelViewMatrixSet(graphicsPipeline?.programShape3D, modelViewMatrix)

            graphics?.drawTriangleStrips(strip.indexBuffer, strip.indices.size)
            graphics?.unlinkBufferFromShaderProgram (graphicsPipeline?.programShape3D)

        }

        /*
        graphics?.blendSetAlpha()

        graphics?.linkBufferToShaderProgram(graphicsPipeline?.programShape3D, vertexBuffer)

        graphics?.uniformsModulateColorSet(graphicsPipeline?.programShape3D, color)
        graphics?.uniformsProjectionMatrixSet(graphicsPipeline?.programShape3D, projectionMatrix)
        graphics?.uniformsModelViewMatrixSet(graphicsPipeline?.programShape3D, modelViewMatrix)

        graphics?.drawTriangleStrips(indexBuffer, 4)
        graphics?.unlinkBufferFromShaderProgram (graphicsPipeline?.programShape3D)
        */

    }

}

