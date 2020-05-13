package shin.ktok.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shin.ktok.R
import shin.ktok.entity.Log
import shin.ktok.utility.DateTool
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recyclerview_item.*

class LogListAdapter : RecyclerView.Adapter<LogListAdapter.WordViewHolder>() {

    //private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var logs = emptyList<Log>() // Cached copy of words

    inner class WordViewHolder(override val containerView: View?) :
        RecyclerView.ViewHolder(containerView!!), LayoutContainer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = logs[position]
        holder.textView.text = current.logText
    }

    internal fun setLogs(words: List<Log>) {
        this.logs = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = logs.size
}