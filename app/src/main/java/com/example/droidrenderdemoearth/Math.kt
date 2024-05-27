package com.example.droidrenderdemoearth

class Math {

    companion object {
        // Static function
        fun rotateX(float3: Float3, radians: Float): Float3 {
            val rotationMatrix = Matrix()
            rotationMatrix.rotateX(radians)
            return rotationMatrix.processRotationOnly(float3)
        }

        fun rotateY(float3: Float3, radians: Float): Float3 {
            val rotationMatrix = Matrix()
            rotationMatrix.rotateY(radians)
            return rotationMatrix.processRotationOnly(float3)
        }

        fun rotateZ(float3: Float3, radians: Float): Float3 {
            val rotationMatrix = Matrix()
            rotationMatrix.rotateZ(radians)
            return rotationMatrix.processRotationOnly(float3)
        }
    }

}