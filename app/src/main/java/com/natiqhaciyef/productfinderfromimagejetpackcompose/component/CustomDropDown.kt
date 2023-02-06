package com.natiqhaciyef.productfinderfromimagejetpackcompose.component


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import com.natiqhaciyef.productfinderfromimagejetpackcompose.data.model.LanguagePrototype
import com.natiqhaciyef.productfinderfromimagejetpackcompose.ui.theme.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDownMenu(
    list: List<LanguagePrototype>,
    selectedOption: MutableState<String>
) {
    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .border(1.dp, MaterialTheme.colors.dropDownSelected, shape = RoundedCornerShape(10.dp)),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            value = selectedOption.value,
            onValueChange = { },
            readOnly = true,
            label = { Text(text = "Languages", color = Color.Gray) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                textColor = MaterialTheme.colors.textColorReverseTheme,
                backgroundColor = MaterialTheme.colors.dropDownBackground
            )
        )

        ExposedDropdownMenu(
            expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
            list.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption.value = option.name
                        expanded = false
                    }) {
                    Text(
                        text = option.name,
                        color = MaterialTheme.colors.textColorReverseTheme
                    )
                }
            }
        }
    }
}


@get: Composable
val Colors.textColorReverseTheme: Color
    get() = if (isLight) Black  else Black

@get: Composable
val Colors.dropDownSelected: Color
    get() = if (isLight) DropDownSelectedLight else DropDownSelectedDark

@get: Composable
val Colors.dropDownBackground: Color
    get() = if (isLight) Color.LightGray else Color.LightGray