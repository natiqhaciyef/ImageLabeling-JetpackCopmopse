package com.natiqhaciyef.productfinderfromimagejetpackcompose.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.productfinderfromimagejetpackcompose.R

@Preview
@Composable
fun HomeScreen(){
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
                modifier = Modifier.padding(20.dp)
            ) {
                 Categories()
            }
        }
    }
}


@Preview
@Composable
fun Categories(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(30.dp))
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(100.dp)
            ) {

            }
            Spacer(modifier = Modifier.width(30.dp))

            Spacer(modifier = Modifier.width(30.dp))
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(100.dp)
            ) {

            }
            Spacer(modifier = Modifier.width(30.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))
        
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(30.dp))
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(100.dp)
            ) {

            }
            Spacer(modifier = Modifier.width(30.dp))

            Spacer(modifier = Modifier.width(30.dp))
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .size(100.dp)
            ) {

            }
            Spacer(modifier = Modifier.width(30.dp))
        }
    }
}


@get: Composable
val Colors.AppTitleTextColor: Color
    get() = if (isLight) Black else Black

@get: Composable
val Colors.AppHomeBackgroundColor: Color
    get() = if (isLight) ExtraLightBlue else Blue