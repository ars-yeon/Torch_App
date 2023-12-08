package com.sylee.torchappwidget.presentation.feature

import android.content.Context
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.sylee.torchappwidget.R
import com.sylee.torchappwidget.databinding.ActivityMainBinding
import com.sylee.torchappwidget.torch.TorchManager

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val torchManager by lazy { TorchManager(getSystemService(Context.CAMERA_SERVICE) as CameraManager) }

    private var isTorchOn = false
    private lateinit var btn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        btn = btnPower
        btn.setOnClickListener {
            toggleTorch()
        }
    }

    private fun toggleTorch() {
        isTorchOn = !isTorchOn

        if (isTorchOn) {
            btn.setImageResource(R.drawable.power_on)
            torchManager.turnOnTorch()
        } else {
            btn.setImageResource(R.drawable.power_off)
            torchManager.turnOffTorch()
        }
    }
}
