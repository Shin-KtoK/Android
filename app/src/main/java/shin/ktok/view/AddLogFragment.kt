package shin.ktok.view


import android.database.SQLException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import shin.ktok.R
import shin.ktok.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_add_log.*
import java.lang.Exception

class AddLogFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_log, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button_save_log_text.setOnClickListener {
            if (edit_word.text.toString() == "") {
                Toast.makeText(activity!!, R.string.empty_not_saved, Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insert(edit_word.text.toString())
            }
            backToMainFragment()
        }
    }

    private fun backToMainFragment() {
        activity!!.findNavController(R.id.fragment)
            .navigate(R.id.action_addLogFragment_to_mainFragment)
    }


}
