package divyansh.tech.wysiwyg.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper
import com.google.android.material.textfield.TextInputEditText


class WYSIWYGEditText : TextInputEditText {
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?) : super(context!!) {}

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection? {
        return CustomInputConnection(
            super.onCreateInputConnection(outAttrs),
            true
        )
    }

    private inner class CustomInputConnection(target: InputConnection?, mutable: Boolean) :
        InputConnectionWrapper(target, mutable) {
        override fun sendKeyEvent(event: KeyEvent): Boolean {
            if (event.action === KeyEvent.ACTION_DOWN && event.keyCode === KeyEvent.KEYCODE_DEL) {
                return super.sendKeyEvent(event)
            } else if (event.action === KeyEvent.ACTION_DOWN && event.keyCode === KeyEvent.KEYCODE_ENTER) {
                return super.sendKeyEvent(event)
            }
            return false
        }

        override fun deleteSurroundingText(beforeLength: Int, afterLength: Int): Boolean {
            // magic: in latest Android, deleteSurroundingText(1, 0) will be called for backspace
            if (beforeLength == 1 && afterLength == 0) {
                // backspace
                val len = text!!.length
                if (len == 0) {
                    return (sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                            && sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL)))
                }
                val selection = selectionStart
                if (selection == 0) return false
            }
            return super.deleteSurroundingText(beforeLength, afterLength)
        }
    }

    companion object {
        const val KEYCODE_REMOVE = 100
    }
}