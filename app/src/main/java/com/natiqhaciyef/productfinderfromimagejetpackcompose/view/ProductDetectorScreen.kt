package com.natiqhaciyef.productfinderfromimagejetpackcompose.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.content.*
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.natiqhaciyef.productfinderfromimagejetpackcompose.network.ObjectDetectorAPI
import coil.compose.rememberImagePainter
import com.google.android.material.snackbar.Snackbar
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.defaults.PredefinedCategory
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.Brown
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.DarkBlue
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.ExtraLightBlue
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.LightBrown
import java.io.IOException

@Composable
fun ProductDetectorScreen() {
    val detectedObject = remember {
        mutableStateOf(mutableListOf<DetectedObject>())
    }
    val imageData = remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        imageData.value = it
    }
    val context = LocalContext.current


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.customBackgroundColor)
    ) {

        imageData.value?.let { image ->
            Image(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
                bitmap = image.asImageBitmap(),
                contentDescription = "Captured image",
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                if (ActivityCompat
                        .checkSelfPermission(
                            context,
                            Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(Manifest.permission.CAMERA),
                        111
                    )
                } else {
                    launcher.launch()
                }
            },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
        ) {
            Text(text = "Click for permission")
        }

        objectDetection(imageData, detectedObject)
    }
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


fun labelDetector(label: MutableState<MutableList<ImageLabel>>, imageData: MutableState<Bitmap?>) {
    if (imageData.value != null) {
        val image = InputImage.fromBitmap(imageData.value!!, 0)
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
        labeler.process(image)
            .addOnSuccessListener { labels ->
                label.value = labels
            }
            .addOnFailureListener { e ->


            }
    }
}

@get: Composable
val Colors.customBackgroundColor: Color
    get() = if (isLight) ExtraLightBlue else DarkBlue

@get: Composable
val Colors.customButtonColor: Color
    get() = if (isLight) LightBrown else LightBrown

@get: Composable
val Colors.themeModeColor: Color
    get() = if (isLight) Color.White else Color.Black

@get: Composable
val Colors.reverseThemeModeColor: Color
    get() = if (isLight) Color.Black else Color.White