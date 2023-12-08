package com.sylee.torchappwidget.torch

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class TorchManager(private val cameraManager: CameraManager) {

    private var cameraId: String? = null

    init {
        setupCamera()
    }

    private fun setupCamera() {
        try {
            val cameraIds = cameraManager.cameraIdList
            for (id in cameraIds) {
                val characteristics = cameraManager.getCameraCharacteristics(id)
                val hasFlash = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
                if (hasFlash == true) {
                    cameraId = id
                    return
                }
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    fun turnOnTorch() {
        toggleTorch(true)
    }

    fun turnOffTorch() {
        toggleTorch(false)
    }

    private fun toggleTorch(enable: Boolean) {
        try {
            if (cameraId != null) {
                cameraManager.setTorchMode(cameraId!!, enable)
            }
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
}