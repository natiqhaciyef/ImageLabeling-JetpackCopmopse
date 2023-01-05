package com.natiqhaciyef.productfinderfromimagejetpackcompose.network

import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.ObjectDetector
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions

class ObjectDetectorAPI {
    private val liveObjectDetectorOptions = ObjectDetectorOptions.Builder()
        .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
        .enableClassification()  // Optional
        .build()

    private val multipleObjectDetectorOptions = ObjectDetectorOptions.Builder()
        .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
        .enableMultipleObjects()
        .enableClassification()  // Optional
        .build()

    val liveObjectDetector = ObjectDetection.getClient(liveObjectDetectorOptions)
    val multipleObjectDetector = ObjectDetection.getClient(multipleObjectDetectorOptions)
}