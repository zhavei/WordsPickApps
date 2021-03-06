package com.example.wordspickapps

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for the [RecyclerView] in [DetailActivity]
 */
class WordAdapter(private val letterId: String, context: Context) :
    RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val filteredWords: List<String>

    init {
        // Retrieve the list of words from res/values/arrays/xml
        val words = context.resources.getStringArray(R.array.words).toList()

        filteredWords = words
            // Returns items in a collection if the conditional clause is true,
            // in this case if an item starts with the given letter,
            // ignoring UPPERCASE or lowercase.
            .filter { it.startsWith(letterId, ignoreCase = false) }
            // Returns a collection that it has shuffled in place
            .shuffled()
            // Returns the first n items as a [List]
            .take(5)
            // Returns a sorted version of that [List]
            .sorted()
    }


    class WordViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById<Button>(R.id.button_item)

    }

    override fun getItemCount(): Int {
        return filteredWords.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layouot = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        layouot.accessibilityDelegate = CostumeAccessibilityDelegate
        return WordViewHolder(layouot)
    }

    /**
     * replaces the content of an existing view with new data
     */
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        val item = filteredWords[position]
        // Needed to call startActivity
        val context = holder.view.context

        // Set the text of the WordViewHolder
        holder.button.text = item
    }

    companion object CostumAccessibilityDelegate : View.AccessibilityDelegate(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ){
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val costumString = host?.context?.getString(R.string.look_up_word)
            val costumClick = AccessibilityNodeInfo.AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK,
                costumString
            )
            info?.addAction(costumClick)
        }
    }

}
