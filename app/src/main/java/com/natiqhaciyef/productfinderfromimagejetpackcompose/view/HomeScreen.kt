package com.natiqhaciyef.productfinderfromimagejetpackcompose.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Beenhere
import androidx.compose.material.icons.filled.GTranslate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.productfinderfromimagejetpackcompose.R

@Preview
@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.AppHomeBackgroundColor
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 65.dp),
                text = "Hi! \nChoose one of the favourite ML and test",
                color = MaterialTheme.colors.AppTitleTextColor,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.notosans_bold_italic)),
                textAlign = TextAlign.Center
            )

            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(top = 40.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                Categories()
            }
        }
    }
}


@Preview
@Composable
fun Categories() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(120.dp),
                elevation = 5.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))

                    Icon(
                        imageVector = Icons.Default.GTranslate,
                        contentDescription = "Translator",
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    
                    Text(
                        text = "Translate",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                        )
                }
            }
            Spacer(modifier = Modifier.width(40.dp))
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(120.dp),
                elevation = 5.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))

                    Icon(
                        imageVector = Icons.Default.Beenhere,
                        contentDescription = "Image Labeling",
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Translate",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(120.dp),
                elevation = 5.dp
            ) {

            }
            Spacer(modifier = Modifier.width(40.dp))
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(120.dp),
                elevation = 5.dp
            ) {

            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}


@get: Composable
val Colors.AppTitleTextColor: Color
    get() = if (isLight) Black else Black

@get: Composable
val Colors.AppHomeBackgroundColor: Color
    get() = if (isLight) ExtraLightBlue else Blue