package divyansh.tech.wysiwyg_web_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.widget.LinearLayoutCompat


class Toolbar : LinearLayoutCompat {
    private var rTextEditorView: TextEditorView? = null

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        init()
    }

    private fun init() {
        for (view in 0 until childCount) {
            val toolButton: View = getChildAt(view)
            if (toolButton is TextEditorButtons) {
                val button: TextEditorButtons = toolButton as TextEditorButtons
                val toolType: Int = button.toolType
                button.setOnClickListener(OnClickListener {
                    if (rTextEditorView != null) {
                        rTextEditorView!!.setFormat(toolType)
                    }
                })
            }
        }
    }

    fun setEditorView(rTextEditorView: TextEditorView) {
        this.rTextEditorView = rTextEditorView
    }
}