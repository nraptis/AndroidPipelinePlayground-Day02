package com.example.droidrenderdemoearth

import java.nio.IntBuffer

class EarthModelDataStrip(var earthModelData: EarthModelData?,
                          var indexV: Int,
                          var graphics: GraphicsLibrary?,
                          var graphicsPipeline: GraphicsPipeline?) {

    val shapeVertexArray: Array<VertexShape3D>
    val shapeVertexBuffer: GraphicsArrayBuffer<VertexShape3D>

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

        earthModelData?.let { _earthModelData ->
            for (indexH in 0 .. EarthModelData.tileCountH) {

                val x1 = _earthModelData.points[indexH][indexV - 1].x
                val y1 = _earthModelData.points[indexH][indexV - 1].y
                val z1 = _earthModelData.points[indexH][indexV - 1].z

                println("indexH = " + indexH + ", x = " + x1)

                val normalX1 = _earthModelData.normals[indexH][indexV - 1].x
                val normalY1 = _earthModelData.normals[indexH][indexV - 1].y
                val normalZ1 = _earthModelData.normals[indexH][indexV - 1].z

                val u1 = _earthModelData.textureCoords[indexH][indexV - 1].x
                val v1 = _earthModelData.textureCoords[indexH][indexV - 1].y

                val x2 = _earthModelData.points[indexH][indexV].x
                val y2 = _earthModelData.points[indexH][indexV].y
                val z2 = _earthModelData.points[indexH][indexV].z

                val normalX2 = _earthModelData.normals[indexH][indexV].x
                val normalY2 = _earthModelData.normals[indexH][indexV].y
                val normalZ2 = _earthModelData.normals[indexH][indexV].z

                val vertexIndex1 = indexH * 2
                val vertexIndex2 = vertexIndex1 + 1

                val vertex1 = shapeVertexArray[vertexIndex1]
                val vertex2 = shapeVertexArray[vertexIndex2]

                vertex1.x = x1
                vertex1.y = y1
                vertex1.z = z1

                vertex2.x = x2
                vertex2.y = y2
                vertex2.z = z2
            }
        }

        shapeVertexBuffer = GraphicsArrayBuffer(graphics, shapeVertexArray)

    }
}