package com.natiqhaciyef.productfinderfromimagejetpackcompose.network

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import java.io.IOException

@ExperimentalGetImage class ImageAnalyzer: ImageAnalysis.Analyzer {
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            
        }
    }
}