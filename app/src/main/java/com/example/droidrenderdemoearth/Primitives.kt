package com.example.droidrenderdemoearth
import java.nio.FloatBuffer

interface Colorable {
    var red: Float
    var green: Float
    var blue: Float
    var alpha: Float
}

interface Positionable2D {
    var x: Float
    var y: Float
}
interface Positionable3D: Positionable2D {
    var z: Float
}

interface Texturable2D {
    var u: Float
    var v: Float
}
interface Texturable3D: Texturable2D {
    var w: Float
}

data class VertexShape2D(override var x: Float, override var y: Float) : FloatBufferable, Positionable2D {

    override fun writeToBuffer(buffer: FloatBuffer) {
        buffer.put(x)
        buffer.put(y)
    }

    override fun size(): Int {
        return 2
    }
}

data class VertexShape3D(override var x: Float, override var y: Float, override var z: Float) : FloatBufferable, Positionable3D {

    override fun writeToBuffer(buffer: FloatBuffer) {
        buffer.put(x)
        buffer.put(y)
        buffer.put(z)
    }

    override fun size(): Int {
        return 3
    }
}

data class VertexSprite2D(override var x: Float, override var y: Float, override var u: Float, override var v: Float) : FloatBufferable, Positionable2D, Texturable2D {

    override fun writeToBuffer(buffer: FloatBuffer) {
        buffer.put(x)
        buffer.put(y)
        buffer.put(u)
        buffer.put(v)
    }

    override fun size(): Int {
        return 4
    }
}

data class VertexSprite3D(override var x: Float, override var y: Float, override var z: Float, override var u: Float, override var v: Float) : FloatBufferable, Positionable3D, Texturable2D {

    override fun writeToBuffer(buffer: FloatBuffer) {
        buffer.put(x)
        buffer.put(y)
        buffer.put(z)

        buffer.put(u)
        buffer.put(v)
    }

    override fun size(): Int {
        return 5
    }
}