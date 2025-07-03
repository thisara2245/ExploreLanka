import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.explorelanka.R // ✅ Use your actual package name here

class ProfileFragment : Fragment() {

    private lateinit var listOptions: ListView
    private val options = listOf("Profile", "Bookmarked", "Previous Trips", "Settings", "Version")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)




        return view
    }
}

