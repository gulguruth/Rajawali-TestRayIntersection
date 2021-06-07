package com.example.testrayintersection

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.rajawali3d.view.ISurface
import org.rajawali3d.view.SurfaceView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var surface = findViewById<SurfaceView>(R.id.raj_surface)
        surface.setFrameRate(60.0)
        surface.setRenderMode(ISurface.RENDERMODE_WHEN_DIRTY)

        var renderer = RayExampleRenderer(this)
        surface.setSurfaceRenderer(renderer)

        findViewById<Button>(R.id.trigger_button).setOnClickListener {
            renderer.triggerRay()
        }
    }
}