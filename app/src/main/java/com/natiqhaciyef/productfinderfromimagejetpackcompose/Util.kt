package com.natiqhaciyef.productfinderfromimagejetpackcompose

import com.natiqhaciyef.productfinderfromimagejetpackcompose.data.Languages


fun languageFinder(languageName: String): String{
    var lanCode = ""
    for (element in Languages.list){
        if (element.name == languageName){
            lanCode = element.type
        }
    }
    return lanCode
}