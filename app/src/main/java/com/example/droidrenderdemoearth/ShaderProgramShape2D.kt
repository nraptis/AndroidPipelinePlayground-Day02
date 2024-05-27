package com.example.droidrenderdemoearth

class ShaderProgramShape2D(name: String, vertexShader: Int, fragmentShader: Int) : ShaderProgram(name, vertexShader, fragmentShader) {
    // Additional properties or methods specific to Sprite2D shaders can be added here

    init {


        attributeLocationPosition = getAttributeLocation("Positions")

        uniformLocationModulateColor = getUniformLocation("ModulateColor")
        uniformLocationProjectionMatrix = getUniformLocation("ProjectionMatrix")
        uniformLocationModelViewMatrix = getUniformLocation("ModelViewMatrix")


        println("===> " + name + " ... " + "attributeLocationPosition = " + attributeLocationPosition)

        println("===> " + name + " ... " + "uniformLocationModulateColor = " + uniformLocationModulateColor)
        println("===> " + name + " ... " + "uniformLocationProjectionMatrix = " + uniformLocationProjectionMatrix)
        println("===> " + name + " ... " + "uniformLocationModelViewMatrix = " + uniformLocationModelViewMatrix)

        attributeStridePosition = Float.SIZE_BYTES * 2
        attributeSizePosition = 2
        attributeOffsetPosition = 0
    }
}
