package com.natiqhaciyef.productfinderfromimagejetpackcompose.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.natiqhaciyef.productfinderfromimagejetpackcompose.network.ObjectDetectorAPI
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.objects.DetectedObject
import com.natiqhaciyef.productfinderfromimagejetpackcompose.R
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.*

@Preview
@Composable
fun ProductLabelingScreen() {
    val detectedObject = remember {
        mutableStateOf(mutableListOf<DetectedObject>())
    }
    val labelDetector = remember {
        mutableStateOf(mutableListOf<ImageLabel>())
    }
    val imageData = remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        imageData.value = it
    }
    val context = LocalContext.current


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.customBackgroundColor)
    ) {

        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Select image and show result",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.reverseThemeModeColor
        )
        Spacer(modifier = Modifier.height(60.dp))

        if (imageData.value != null) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                bitmap = imageData.value!!.asImageBitmap(),
                contentDescription = "Captured image",
                contentScale = ContentScale.Fit
            )
        } else {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.select_pircture_image),
                contentDescription = "Captured image",
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

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
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.customButtonColor
            )
        ) {
            Text(
                text = "Select image",
                fontSize = 18.sp,
                color = MaterialTheme.colors.reverseThemeModeColor
            )
        }

        labelDetector(labelDetector, imageData)

        if (detectedObject.value != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 30.dp),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    items(items = labelDetector.value) { label ->
                        DetectedObjectCard(label = label)
                    }
                }
            }
        }
    }
}

@Composable
fun DetectedObjectCard(label: ImageLabel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 10.dp, vertical = 7.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.themeModeColor
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(text = "Item category: ", color = MaterialTheme.colors.reverseThemeModeColor)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "${label.text}",
                    color = MaterialTheme.colors.reverseThemeModeColor
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Row {
                Text(text = "Confidence: ", color = MaterialTheme.colors.reverseThemeModeColor)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "${label.confidence}",
                    color = MaterialTheme.colors.reverseThemeModeColor
                )
            }
        }

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
            .addOnFailureListener { e -> }
    }
}

@get: Composable
val Colors.customBackgroundColor: Color
    get() = if (isLight) ExtraLightBlue else DarkBlue

@get: Composable
val Colors.customButtonColor: Color
    get() = if (isLight) LightBrown else DarkBrown

@get: Composable
val Colors.themeModeColor: Color
    get() = if (isLight) Color.White else Color.Black

@get: Composable
val Colors.reverseThemeModeColor: Color
    get() = if (isLight) Color.Black else Color.White