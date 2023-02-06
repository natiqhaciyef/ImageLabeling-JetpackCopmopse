package com.natiqhaciyef.productfinderfromimagejetpackcompose.network

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class LanguageTranslatorAPI {
    var conditions = DownloadConditions.Builder()
        .requireWifi()
        .build()

    fun translateOptions(source: String, target: String) = TranslatorOptions.Builder()
        .setSourceLanguage(source)
        .setTargetLanguage(target)
        .build()
}