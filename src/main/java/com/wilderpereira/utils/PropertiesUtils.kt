package com.wilderpereira.utils

import java.io.IOException
import java.io.InputStream
import java.util.Properties

object PropertiesUtils {

    private val propertiesFileName = "application.properties"

    fun readKey(key: String): String {
        val loader = Thread.currentThread().contextClassLoader
        val props = Properties()
        try {
            loader.getResourceAsStream(propertiesFileName).use { resourceStream -> props.load(resourceStream) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return props.getProperty(key)
    }

}
