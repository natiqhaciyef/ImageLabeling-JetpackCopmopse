package com.natiqhaciyef.productfinderfromimagejetpackcompose

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.google.android.material.snackbar.Snackbar
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.defaults.PredefinedCategory
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.ProductFinderFromImageJetpackComposeTheme
import com.natiqhaciyef.productfinderfromimagejetpackcompose.view.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {

            }
        }
    }
}


@Composable
fun AppTheme(content: @Composable () -> Unit) {
    ProductFinderFromImageJetpackComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
//            ProductDetectorScreen()
            ProductDetectorScreen2()
        }
    }
}

@Composable
fun ProductDetectorScreen2() {
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
        Spacer(modifier = Modifier.height(120.dp))

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
                    .height(300.dp),
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
        objectDetection(imageData, detectedObject)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {

    }
}