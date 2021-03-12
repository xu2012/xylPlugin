package android.src.main.kotlin.com.flutter.plugin.xyl

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/9/17 11:10
 * @version 2.2
 */
class FileUtils {
    companion object {
        fun saveBitmapFile(bitmap: Bitmap, context: Context): File? {
            val file = File("${context.externalCacheDir}/liveness.png")
            try {
                val bos = BufferedOutputStream(FileOutputStream(file))
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
                bos.flush()
                bos.close()
                return file
            } catch (e: Exception) {
                Log.e("xu",e.localizedMessage)
            }
            return null
        }
    }
}