package com.example.droidrenderdemoearth



class Zig(graphicsPipeline: GraphicsPipeline?,
          graphics: GraphicsLibrary?,
          var texture: GraphicsTexture?,
          var earth: Earth) {

    var color = Color(1.0f, 1.0f, 1.0f, 0.5f)

    var projectionMatrix = Matrix()
    var modelViewMatrix = Matrix()

    val graphics: GraphicsLibrary?
    val graphicsPipeline: GraphicsPipeline?

    private var svn = 0.0f

    init {

        this.graphics = graphics
        this.graphicsPipeline = graphicsPipeline

        projectionMatrix.ortho(graphics?.widthf ?: 0.0f,
            graphics?.heightf ?: 0.0f)

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


        modelViewMatrix.translation(width * 3.0f / 4.0f, height * 3.0f / 4.0f, 0.0f)
        modelViewMatrix.rotateY(spin)
        modelViewMatrix.scale(0.5f)

        for (strip in earth.earthModelDataStrips) {

            graphics?.blendSetAlpha()

            graphics?.linkBufferToShaderProgram(graphicsPipeline?.programSprite3D, strip.spriteVertexBuffer)

            graphics?.uniformsTextureSet(graphicsPipeline?.programSprite2D, texture)
            graphics?.uniformsModulateColorSet(graphicsPipeline?.programSprite3D, color)
            graphics?.uniformsProjectionMatrixSet(graphicsPipeline?.programSprite3D, projectionMatrix)
            graphics?.uniformsModelViewMatrixSet(graphicsPipeline?.programSprite3D, modelViewMatrix)

            graphics?.drawTriangleStrips(strip.indexBuffer, strip.indices.size)
            graphics?.unlinkBufferFromShaderProgram (graphicsPipeline?.programSprite3D)

        }

    }

}

