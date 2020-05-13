package shin.ktok.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import shin.ktok.R
import shin.ktok.view.adapter.LogListAdapter
import shin.ktok.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private val logListAdapter: LogListAdapter by lazy {
        LogListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(recyclerview){
            layoutManager = LinearLayoutManager(activity!!)
            adapter = logListAdapter
        }

        fab.setOnClickListener {
            findNavController().navigate(R.id.addLogFragment)
        }
        fab_del.setOnClickListener {
            viewModel.deleteAll()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.allLog.observe(activity!!, Observer { logs ->
            logs?.let {
                logListAdapter.run {
                    setLogs(it)
                    notifyDataSetChanged()
                }
            }
        })
    }
}
