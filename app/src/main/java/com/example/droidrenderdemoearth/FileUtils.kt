package com.example.droidrenderdemoearth

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStreamReader

object FileUtils {

    fun getNumberOfBytesFromAsset(context: Context?, fileName: String?): Int {
        context?.let { _context ->
            fileName?.let { _fileName ->

                try {
                    val inputStream = _context.assets.open(_fileName)
                    val numberOfBytes = inputStream.available()
                    inputStream.close() // Close the stream after getting the length
                    return numberOfBytes
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return 0
    }

    fun readFileFromAsset(context: Context?, fileName: String?): ByteArray? {
        context?.let { _context ->
            fileName?.let { _fileName ->
                val numberOfBytes = getNumberOfBytesFromAsset(_context, _fileName)
                if (numberOfBytes > 0) {
                    val inputStream = _context.assets.open(_fileName)
                    val buffer = ByteArray(numberOfBytes)
                    try {
                        // Read all bytes into the buffer
                        inputStream.read(buffer)
                        return buffer
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        try {
                            inputStream.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
        return null
    }

    fun readFileFromAssetAsString(context: Context?, fileName: String?): String? {
        val fileContents =  readFileFromAsset(context, fileName)
        fileContents?.let {
            return String(it)
        }
        return null
    }

    fun readFileFromAssetAsBitmap(context: Context?, fileName: String?): Bitmap? {
        val fileContents =  readFileFromAsset(context, fileName)
        fileContents?.let {
            return BitmapFactory.decodeByteArray(it, 0, it.size)
        }
        return null
    }



}