package com.natiqhaciyef.productfinderfromimagejetpackcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.mlkit.nl.translate.Translation
import com.natiqhaciyef.productfinderfromimagejetpackcompose.component.CustomDropDownMenu
import com.natiqhaciyef.productfinderfromimagejetpackcompose.data.Languages
import com.natiqhaciyef.productfinderfromimagejetpackcompose.languageFinder
import com.natiqhaciyef.productfinderfromimagejetpackcompose.network.LanguageTranslatorAPI
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.*

@Composable
fun GoogleTranslateScreen() {
    val api = LanguageTranslatorAPI()
    val source = remember { mutableStateOf("") }
    val target = remember { mutableStateOf("") }
    val textInput = remember { mutableStateOf("") }
    val textResult = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(horizontal = 10.dp),
            elevation = 7.dp,
            shape = RoundedCornerShape(15.dp)
        ) {
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    disabledIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    backgroundColor = Transparent,
                    textColor = Color.Black
                ),
                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                value = textInput.value,
                placeholder = { Text(text = "Enter the text", fontSize = 18.sp) },
                onValueChange = {
                    textInput.value = it
                })
        }

        Spacer(modifier = Modifier.height(20.dp))

        CustomDropDownMenu(list = Languages.list, selectedOption = source)

        Spacer(modifier = Modifier.height(5.dp))
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Icon",
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))

        CustomDropDownMenu(list = Languages.list, selectedOption = target)


        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(horizontal = 10.dp),
            elevation = 7.dp,
            shape = RoundedCornerShape(15.dp)
        ) {

            if (source.value.isNotEmpty() && target.value.isNotEmpty()) {
                val translator = Translation
                    .getClient(
                        api.translateOptions(
                            languageFinder(source.value),
                            languageFinder(target.value)
                        )
                    )
                translator.downloadModelIfNeeded(api.conditions)
                translator.translate(textInput.value).addOnSuccessListener {
                    textResult.value = it
                }
            }
            TextField(
                value = textResult.value,
                onValueChange = { },
                readOnly = true,
                placeholder = { Text(text = "Result...", fontSize = 18.sp) },
                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                colors = TextFieldDefaults.textFieldColors(
                    disabledIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    focusedIndicatorColor = Transparent,
                    backgroundColor = Transparent,
                    textColor = Color.Black
                )
            )

        }
    }
}
