package com.example.droidrenderdemoearth

import android.content.Context
import android.opengl.GLSurfaceView
class GraphicsSurfaceView(var activity: GraphicsActivity?) : GLSurfaceView(activity) {

    //val width: Int = this.resources.displayMetrics.widthPixels
    //val height: Int = this.resources.displayMetrics.heightPixels

    private lateinit var renderer: GraphicsRenderer
    init {
        print("WE INITISINIS")

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Access width and height here
        val width = w
        val height = h

        print("SURFACE CHANGED: WIDTH: " + w)
        print("SURFACE CHANGED: HEOGHT: " + h)

        setEGLContextClientVersion(2)
        renderer = GraphicsRenderer(context, activity,this, width, height)
        setRenderer(renderer)
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

}