package cafe.adriel.chroma.ktx

import android.util.Log

fun logError(error: Throwable) {
    Log.e("Chroma", error.message.orEmpty(), error)
}
