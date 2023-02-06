package com.natiqhaciyef.productfinderfromimagejetpackcompose.data.model

import com.google.mlkit.nl.translate.TranslateLanguage

data class LanguagePrototype(
    val name: String,
    val type: String, // Translate Language type
    val image: Int
)