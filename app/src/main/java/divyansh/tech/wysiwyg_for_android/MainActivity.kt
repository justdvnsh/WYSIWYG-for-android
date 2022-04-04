package divyansh.tech.wysiwyg_for_android

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import divyansh.tech.wysiwyg_web_view.TextEditorButtons
import divyansh.tech.wysiwyg_web_view.TextEditorView
import divyansh.tech.wysiwyg_web_view.Toolbar


class MainActivity() : AppCompatActivity(),
    ColorPickerDialogListener {
    private lateinit var editor: TextEditorView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editor = findViewById(R.id.editor_view)
        // Enable keyboard's incognito mode
        editor.setIncognitoModeEnabled(true)
        val editorToolbar: Toolbar = findViewById(R.id.editor_toolbar)
        editorToolbar.setEditorView(editor)

        // Set initial content
        editor.setHtml(
            "<p><b><i><u><strike>"
                    + "The quick brown fox jumps over the lazy dog."
                    + "</strike></u></i></b></p>"
        )

        // Listen to the editor's text changes
        editor.setOnTextChangeListener(object : TextEditorView.OnTextChangeListener {
            override fun onTextChanged(content: String?) {
                Log.d(TAG, "onTextChanged: $content")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.action_undo) {
            editor.undo()
            return true
        } else if (id == R.id.action_redo) {
            editor.redo()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        editor.setOnTextChangeListener(null)
        editor.removeAllViews()
        editor.destroy()
    }

    override fun onColorSelected(dialogId: Int, color: Int) {
        if (dialogId == DIALOG_TEXT_FORE_COLOR_ID) {
            editor.setTextColor(color)
        } else if (dialogId == DIALOG_TEXT_BACK_COLOR_ID) {
            editor.setTextBackgroundColor(color)
        }
    }

    override fun onDialogDismissed(dialogId: Int) {}

    companion object {
        private val TAG = "RTextEditorView"
        private val DIALOG_TEXT_FORE_COLOR_ID = 0
        private val DIALOG_TEXT_BACK_COLOR_ID = 1
    }
}