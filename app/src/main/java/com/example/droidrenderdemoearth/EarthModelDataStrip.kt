package com.example.droidrenderdemoearth

import java.nio.IntBuffer

class EarthModelDataStrip(var earthModelData: EarthModelData?,
                          var indexV: Int,
                          var graphics: GraphicsLibrary?,
                          var graphicsPipeline: GraphicsPipeline?) {

    val shapeVertexArray: Array<VertexShape3D>
    val shapeVertexBuffer: GraphicsArrayBuffer<VertexShape3D>

    val spriteVertexArray: Array<VertexSprite3D>
    val spriteVertexBuffer: GraphicsArrayBuffer<VertexSprite3D>

    val indices: IntArray
    val indexBuffer: IntBuffer

    init {

        //this.graphics = graphics
        //this.graphicsPipeline = graphicsPipeline

        val indexCount = (EarthModelData.tileCountH + 1) * 2
        indices = IntArray(indexCount) { it }
        indexBuffer = graphics?.indexBufferGenerate(indices) ?: IntBuffer.allocate(0)

        shapeVertexArray = Array(indexCount) {
            VertexShape3D(0.0f, 0.0f, 0.0f)
        }

        spriteVertexArray = Array(indexCount) {
            VertexSprite3D(0.0f, 0.0f, 0.0f, 0.0f, 0.0f)
        }

        earthModelData?.let { _earthModelData ->
            for (indexH in 0 .. EarthModelData.tileCountH) {

                val vertexIndex1 = indexH * 2
                val vertexIndex2 = vertexIndex1 + 1

                val vertex1 = shapeVertexArray[vertexIndex1]
                val vertex2 = shapeVertexArray[vertexIndex2]

                vertex1.x = _earthModelData.points[indexH][indexV - 1].x
                vertex1.y = _earthModelData.points[indexH][indexV - 1].y
                vertex1.z = _earthModelData.points[indexH][indexV - 1].z

                vertex2.x = _earthModelData.points[indexH][indexV].x
                vertex2.y = _earthModelData.points[indexH][indexV].y
                vertex2.z = _earthModelData.points[indexH][indexV].z


                //spriteVertexArray
            }

            for (indexH in 0 .. EarthModelData.tileCountH) {

                val vertexIndex1 = indexH * 2
                val vertexIndex2 = vertexIndex1 + 1

                val vertex1 = spriteVertexArray[vertexIndex1]
                val vertex2 = spriteVertexArray[vertexIndex2]

                vertex1.x = _earthModelData.points[indexH][indexV - 1].x
                vertex1.y = _earthModelData.points[indexH][indexV - 1].y
                vertex1.z = _earthModelData.points[indexH][indexV - 1].z

                vertex1.u = _earthModelData.textureCoords[indexH][indexV - 1].x
                vertex1.v = _earthModelData.textureCoords[indexH][indexV - 1].y

                vertex2.x = _earthModelData.points[indexH][indexV].x
                vertex2.y = _earthModelData.points[indexH][indexV].y
                vertex2.z = _earthModelData.points[indexH][indexV].z

                vertex2.u = _earthModelData.textureCoords[indexH][indexV].x
                vertex2.v = _earthModelData.textureCoords[indexH][indexV].y

            }
        }

        shapeVertexBuffer = GraphicsArrayBuffer(graphics, shapeVertexArray)
        spriteVertexBuffer = GraphicsArrayBuffer(graphics, spriteVertexArray)


    }
}