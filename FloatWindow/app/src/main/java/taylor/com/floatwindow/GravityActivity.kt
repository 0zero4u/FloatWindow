package taylor.com.floatwindow

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import taylor.com.animation_dsl.animSet
import taylor.com.floatwindow.FloatWindow.FLAG_BOTTOM
import taylor.com.floatwindow.FloatWindow.FLAG_LEFT
import taylor.com.floatwindow.FloatWindow.FLAG_MID
import taylor.com.floatwindow.FloatWindow.FLAG_RIGHT
import taylor.com.floatwindow.FloatWindow.FLAG_TOP

class GravityActivity : AppCompatActivity() {

    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gravity_activity)

        findViewById<Button>(R.id.btnGravityTop).setOnClickListener {
            showTopGravityWindow()
        }

        findViewById<Button>(R.id.btnGravityBottom).setOnClickListener {
            showBottomGravityWindow()
        }

        findViewById<Button>(R.id.btnGravityLeft).setOnClickListener {
            showLeftGravityWindow()
        }

        findViewById<Button>(R.id.btnGravityRight).setOnClickListener {
            showRightGravityWindow()
        }
    }

    private fun showTopGravityWindow() {
        val view = LayoutInflater.from(this).inflate(R.layout.gravity_vertical_window, null)
        val windowInfo = FloatWindow.WindowInfo(view).apply {
            width = DimensionUtil.dp2px(300.0)
            height = DimensionUtil.dp2px(80.0)
        }

        FloatWindow.show(this, "top", windowInfo, FLAG_TOP or FLAG_MID) { info ->
            val anim = animSet {
                anim {
                    values = intArrayOf(info.layoutParams?.y ?: 0, 0)
                    interpolator = LinearOutSlowInInterpolator()
                    duration = 250L
                    action = { value -> FloatWindow.updateWindowView(y = value as Int) }
                }
            }
            anim.start()
            handler.postDelayed({ anim.reverse() }, 1500)
        }
    }

    private fun showBottomGravityWindow() {
        val view = LayoutInflater.from(this).inflate(R.layout.gravity_vertical_window, null)
        val windowInfo = FloatWindow.WindowInfo(view).apply {
            width = DimensionUtil.dp2px(300.0)
            height = DimensionUtil.dp2px(80.0)
        }

//        FloatWindow.show(this, "bottom", windowInfo, FLAG_BOTTOM or FLAG_MID) { windowInfo ->
//            windowInfo?.layoutParams?.y?.let {
//                ValueAnimator.ofInt(it, it - windowInfo.height).apply {
//                    interpolator = LinearOutSlowInInterpolator()
//                    duration = 250L
//                    addUpdateListener { animation -> FloatWindow.updateWindowView(y = animation.animatedValue as Int) }
//                    start()
//                }
//            }
//        }
        FloatWindow.show(this, "bottom", windowInfo, FLAG_BOTTOM or FLAG_MID, duration = 900L, stayTime = 1000L)
    }

    private fun showLeftGravityWindow() {
        val view = LayoutInflater.from(this).inflate(R.layout.gravity_horizontal_window, null)
        val windowInfo = FloatWindow.WindowInfo(view).apply {
            width = DimensionUtil.dp2px(80.0)
            height = DimensionUtil.dp2px(200.0)
        }

//        FloatWindow.show(this, "left", windowInfo, FLAG_LEFT or FLAG_MID) { windowInfo ->
//            windowInfo?.layoutParams?.x?.let {
//                ValueAnimator.ofInt(it, 0).apply {
//                    interpolator = LinearOutSlowInInterpolator()
//                    duration = 250L
//                    addUpdateListener { animation -> FloatWindow.updateWindowView(x = animation.animatedValue as Int) }
//                    start()
//                }
//            }
//        }

        FloatWindow.show(this, "left", windowInfo, FLAG_LEFT or FLAG_MID, duration = 500L, stayTime = 1000L)
    }

    private fun showRightGravityWindow() {
        val view = LayoutInflater.from(this).inflate(R.layout.gravity_horizontal_window, null)
        val windowInfo = FloatWindow.WindowInfo(view).apply {
            width = DimensionUtil.dp2px(80.0)
            height = DimensionUtil.dp2px(200.0)
        }

//        FloatWindow.show(this, "right", windowInfo, FLAG_RIGHT or FLAG_MID, offset = -30) { windowInfo ->
//            windowInfo?.layoutParams?.x?.let {
//                ValueAnimator.ofInt(it, it - windowInfo.width).apply {
//                    interpolator = LinearOutSlowInInterpolator()
//                    duration = 250L
//                    addUpdateListener { animation -> FloatWindow.updateWindowView(x = animation.animatedValue as Int) }
//                    start()
//                }
//            }
//        }

        FloatWindow.show(this, "right", windowInfo, FLAG_RIGHT or FLAG_MID, offset = -100, duration = 500L, stayTime = 3000L)
    }
}