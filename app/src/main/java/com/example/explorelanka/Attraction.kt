import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attraction(
    val name: String,
    val description: String,
    val imageResId: Int
) : Parcelable
