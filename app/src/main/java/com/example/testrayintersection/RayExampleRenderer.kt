package com.example.testrayintersection

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.MotionEvent
import org.rajawali3d.lights.DirectionalLight
import org.rajawali3d.materials.Material
import org.rajawali3d.materials.methods.DiffuseMethod.Lambert
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.primitives.Sphere
import org.rajawali3d.renderer.Renderer
import org.rajawali3d.util.Intersector


class RayExampleRenderer(context : Context?) : Renderer(context) {

    init {
        setFrameRate(60)
    }

    lateinit var directionalLight : DirectionalLight
    lateinit var mSphere : Sphere

    lateinit var mNearPos: Vector3
    lateinit var mFarPos: Vector3


    override fun onOffsetsChanged(
        xOffset: Float,
        yOffset: Float,
        xOffsetStep: Float,
        yOffsetStep: Float,
        xPixelOffset: Int,
        yPixelOffset: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onTouchEvent(event: MotionEvent?) {
        TODO("Not yet implemented")
    }

    override fun initScene() {
        directionalLight = DirectionalLight(1.0, .2, (-1.0f).toDouble())
        directionalLight.setColor(1.0f, 1.0f, 1.0f)
        directionalLight.power = 2f
        currentScene.addLight(directionalLight)

        val material = Material()
        material.enableLighting(true)
        material.diffuseMethod = Lambert()
        material.color = Color.GREEN

        mSphere = Sphere(.5f, 24, 24)
        mSphere.material = material

        currentScene.addChild(mSphere)
        currentScene.backgroundColor = Color.WHITE

        currentCamera.position = Vector3(0.0, 0.0, 4.0)
        currentCamera.lookAt = Vector3.ZERO
    }

    fun triggerRay(){
        mSphere.geometry.boundingSphere

        mNearPos = Vector3(0.0, 0.0, 4.0)
        mFarPos = Vector3(0.0, 0.0, 0.0)

        val hitPoint = Vector3()

        mSphere.geometry.boundingSphere?.radius?.let { rad ->
            Intersector.intersectRaySphere(
                mNearPos,
                mFarPos,
                mSphere.geometry.boundingSphere?.position,
                rad,
                hitPoint
            )
        }

        Log.e("RayExampleRenderer", "hitPoint: $hitPoint")
    }
}
