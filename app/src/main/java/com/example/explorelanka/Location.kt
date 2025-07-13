import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val name: String,
    val headerImageResId: Int,
    val attractions: List<Attraction>
) : Parcelable

