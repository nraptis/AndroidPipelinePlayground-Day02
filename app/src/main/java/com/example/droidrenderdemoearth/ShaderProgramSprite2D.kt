package com.example.droidrenderdemoearth

import android.opengl.GLES20

class ShaderProgramSprite2D(name: String, vertexShader: Int, fragmentShader: Int) : ShaderProgram(name, vertexShader, fragmentShader) {
    // Additional properties or methods specific to Sprite2D shaders can be added here

    init {


        attributeLocationPosition = getAttributeLocation("Positions")
        attributeLocationTextureCoordinates = getAttributeLocation("TextureCoordinates")

        uniformLocationTexture = getUniformLocation("Texture")
        uniformLocationModulateColor = getUniformLocation("ModulateColor")
        uniformLocationProjectionMatrix = getUniformLocation("ProjectionMatrix")
        uniformLocationModelViewMatrix = getUniformLocation("ModelViewMatrix")


        println("===> " + name + " ... " + "attributeLocationPosition = " + attributeLocationPosition)
        println("===> " + name + " ... " + "attributeLocationTextureCoordinates = " + attributeLocationTextureCoordinates)

        println("===> " + name + " ... " + "uniformLocationTexture = " + uniformLocationTexture)
        println("===> " + name + " ... " + "uniformLocationModulateColor = " + uniformLocationModulateColor)
        println("===> " + name + " ... " + "uniformLocationProjectionMatrix = " + uniformLocationProjectionMatrix)
        println("===> " + name + " ... " + "uniformLocationModelViewMatrix = " + uniformLocationModelViewMatrix)

        attributeStridePosition = Float.SIZE_BYTES * 4
        attributeSizePosition = 2
        attributeOffsetPosition = 0

        attributeStrideTextureCoordinates = Float.SIZE_BYTES * 4
        attributeSizeTextureCoordinates = 2
        attributeOffsetTextureCoordinates = Float.SIZE_BYTES * 2
    }
}
