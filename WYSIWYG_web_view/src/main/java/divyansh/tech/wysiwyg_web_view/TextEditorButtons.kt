package divyansh.tech.wysiwyg_web_view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton


class TextEditorButtons : AppCompatImageButton {
    @get:ToolType
    @ToolType
    var toolType = 0

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TextEditorButtons,
            0, 0
        )
        toolType = try {
            a.getInteger(R.styleable.TextEditorButtons_toolType, 0)
        } finally {
            a.recycle()
        }
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
    }
}