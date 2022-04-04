package divyansh.tech.wysiwyg.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout

class HtmlParser(_context: Context) {
    private val context: Context = _context
    var parentView: LinearLayout = LinearLayout(context)

    companion object {
        fun matchesTag(test: String?): Boolean {
            for (tag in HtmlTag.values()) {
                if (tag.name == test) {
                    return true
                }
            }
            return false
        }
    }

    init {
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        parentView.layoutParams = params
    }
}

enum class HtmlTag {
    h1, h2, h3, ul, br, hr, blockquote
}