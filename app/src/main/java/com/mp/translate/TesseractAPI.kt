package com.mp.translate
import android.os.Environment
import com.googlecode.tesseract.android.TessBaseAPI
import java.io.File

class TesseractAPI {
    // Create Tesseract instance
    val tess: TessBaseAPI = TessBaseAPI()

// Given path must contain subdirectory `tessdata` where are `*.traineddata` language files
    val dataPath: String = File(Environment.getExternalStorageDirectory(), "tesseract").getAbsolutePath();


// Initialize API for specified language (can be called multiple times during Tesseract lifetime)
    tess.init(dataPath, "eng");

// Specify image and then recognize it and get result (can be called multiple times during Tesseract lifetime)
    tess.setImage(image);
    val text: String = tess.getUTF8Text();

// Release Tesseract when you don't want to use it anymore
    tess.recycle();
}