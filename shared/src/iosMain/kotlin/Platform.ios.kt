import com.santimattius.kmp.skeleton.shared.MR
import dev.icerock.moko.resources.getImageByFileName
import platform.UIKit.UIDevice
import platform.UIKit.UIImage

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


fun image(name: String): UIImage? {
    return MR.images.getImageByFileName(name)?.toUIImage()
}