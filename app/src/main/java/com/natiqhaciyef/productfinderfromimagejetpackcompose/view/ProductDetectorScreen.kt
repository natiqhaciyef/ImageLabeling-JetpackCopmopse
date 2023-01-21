package com.natiqhaciyef.productfinderfromimagejetpackcompose.view

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.DetectedObject
import com.natiqhaciyef.productfinderfromimagejetpackcompose.network.ObjectDetectorAPI


@Composable
fun ProductDetectorScreen(){

}


fun objectDetection(
    imageData: MutableState<Bitmap?>,
    detectedObjects: MutableState<MutableList<DetectedObject>>
) {
    try {
        var image: InputImage
        if (imageData.value != null) {
            image = InputImage.fromBitmap(imageData.value!!, 0)

            val detector = ObjectDetectorAPI()
            detector.multipleObjectDetector.process(image)
                .addOnSuccessListener { detected ->
                    detectedObjects.value = detected
//                    for (detectedObject in detectedObjects) {
//                        val trackingId = detectedObject.trackingId
//
//                        for (label in detectedObject.labels) {
//                            val text = label.text
//                            Log.e("DETECTOR-ITEMS", text)
//                            val index = label.index
//                            Log.e("DETECTOR-ITEMS", "$index")
//                            val confidence = label.confidence
//                            Log.e("DETECTOR-ITEMS", "$confidence")
//
//                        }
//                    }
                }
                .addOnFailureListener { e ->

                }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}