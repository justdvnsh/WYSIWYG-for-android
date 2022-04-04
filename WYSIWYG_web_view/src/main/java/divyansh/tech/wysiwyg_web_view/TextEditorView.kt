package divyansh.tech.wysiwyg_web_view

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.webkit.*
import androidx.annotation.ColorInt
import androidx.core.view.inputmethod.EditorInfoCompat


class TextEditorView : WebView {
    private var isReady = false
    private var isIncognitoModeEnabled = false
    var html: String? = null
        private set
    private var onTextChangeListener: OnTextChangeListener? = null

    interface OnTextChangeListener {
        fun onTextChanged(content: String?)
    }

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    private fun init() {
        val settings = settings
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        webChromeClient = WebChromeClient()
        webViewClient = RTextEditorWebViewClient()
        addJavascriptInterface(this, "RTextEditorView")
        loadUrl(ASSETS_EDITOR_HTML)
    }

    override fun destroy() {
        exec("javascript:destroy();")
        val parent = parent as ViewGroup
        parent?.removeView(this)
        super.destroy()
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        val inputConnection = super.onCreateInputConnection(outAttrs)
        if (isIncognitoModeEnabled) {
            outAttrs.imeOptions = (outAttrs.imeOptions
                    or EditorInfoCompat.IME_FLAG_NO_PERSONALIZED_LEARNING)
        }
        return inputConnection
    }

    fun setIncognitoModeEnabled(enabled: Boolean) {
        isIncognitoModeEnabled = enabled
    }

    fun setOnTextChangeListener(listener: OnTextChangeListener?) {
        onTextChangeListener = listener
    }

    @JavascriptInterface
    fun onEditorContentChanged(content: String?) {
        if (onTextChangeListener != null) {
            onTextChangeListener!!.onTextChanged(content)
        }
        html = content
    }

    @JavascriptInterface
    fun updateCurrentStyle(style: String?) {
        // TODO: Update buttons state
    }

    fun enable() {
        exec("javascript:enable();")
    }

    fun disable() {
        exec("javascript:disable();")
    }

    fun undo() {
        exec("javascript:undo();")
    }

    fun redo() {
        exec("javascript:redo();")
    }

    fun clear() {
        exec("javascript:clear();")
    }

    fun focus() {
        exec("javascript:setFocus();")
    }

    fun setHtml(html: String) {
        exec("javascript:setHtml('$html');")
    }

    fun setBold() {
        exec("javascript:setBold();")
    }

    fun setItalic() {
        exec("javascript:setItalic();")
    }

    fun setUnderline() {
        exec("javascript:setUnderline();")
    }

    fun setStrikeThrough() {
        exec("javascript:setStrikeThrough();")
    }

    fun removeFormat() {
        exec("javascript:removeFormat();")
    }

    fun setFontSize(sizeInPx: Int) {
        exec("javascript:setFontSize($sizeInPx);")
    }

    fun setNormal() {
        exec("javascript:setNormal();")
    }

    private fun setHeading(value: Int) {
        exec("javascript:setHeading('$value');")
    }

    fun setLineHeight(heightInPx: Int) {
        exec("javascript:setLineHeight($heightInPx);")
    }

    fun setSuperscript() {
        exec("javascript:setSuperscript();")
    }

    fun setSubscript() {
        exec("javascript:setSubscript()")
    }

    fun setTextColor(@ColorInt color: Int) {
        setTextColor(String.format("#%06X", 0xFFFFFF and color))
    }

    fun setTextColor(hexColor: String) {
        exec("javascript:setTextForeColor('$hexColor');")
    }

    fun setTextBackgroundColor(@ColorInt color: Int) {
        setTextBackgroundColor(String.format("#%06X", 0xFFFFFF and color))
    }

    fun setTextBackgroundColor(hexColor: String) {
        exec("javascript:setTextBackColor('$hexColor');")
    }

    fun setBlockCode() {
        exec("javascript:setBlockCode();")
    }

    fun setUnorderedList() {
        exec("javascript:insertUnorderedList();")
    }

    fun setOrderedList() {
        exec("javascript:insertOrderedList();")
    }

    fun setBlockQuote() {
        exec("javascript:setBlockQuote();")
    }

    fun setAlignLeft() {
        exec("javascript:setAlignLeft();")
    }

    fun setAlignCenter() {
        exec("javascript:setAlignCenter();")
    }

    fun setAlignRight() {
        exec("javascript:setAlignRight();")
    }

    fun setAlignJustify() {
        exec("javascript:setAlignJustify();")
    }

    fun insertHorizontalRule() {
        exec("javascript:insertHorizontalRule();")
    }

    fun setIndent() {
        exec("javascript:indent();")
    }

    fun setOutdent() {
        exec("javascript:outdent();")
    }

    fun insertTable(colCount: Int, rowCount: Int) {
        exec("javascript:insertTable('" + colCount + "x" + rowCount + "');")
    }

    fun insertLink(title: String, url: String) {
        exec("javascript:insertLink('$title','$url');")
    }

    fun setUnlink() {
        exec("javascript:unlink();")
    }

    fun insertText(text: String) {
        exec("javascript:insertText('$text');")
    }

    fun editHtml() {
        exec("javascript:editHtml();")
    }

    fun setFormat(@ToolType type: Int) {
        when (type) {
            ToolType.BOLD -> setBold()
            ToolType.ITALIC -> setItalic()
            ToolType.UNDERLINE -> setUnderline()
            ToolType.STRIKETHROUGH -> setStrikeThrough()
            ToolType.REMOVE_FORMAT -> removeFormat()
            ToolType.NORMAL -> setNormal()
            ToolType.H1 -> setHeading(1)
            ToolType.H2 -> setHeading(2)
            ToolType.H3 -> setHeading(3)
            ToolType.H4 -> setHeading(4)
            ToolType.H5 -> setHeading(5)
            ToolType.H6 -> setHeading(6)
            ToolType.SUPERSCRIPT -> setSuperscript()
            ToolType.SUBSCRIPT -> setSubscript()
            ToolType.CODE -> setBlockCode()
            ToolType.UNORDERED_LIST -> setUnorderedList()
            ToolType.ORDERED_LIST -> setOrderedList()
            ToolType.QUOTE -> setBlockQuote()
            ToolType.ALIGN_LEFT -> setAlignLeft()
            ToolType.ALIGN_CENTER -> setAlignCenter()
            ToolType.ALIGN_RIGHT -> setAlignRight()
            ToolType.ALIGN_JUSTIFY -> setAlignJustify()
            ToolType.HORIZONTAL_RULE -> insertHorizontalRule()
            ToolType.INDENT -> setIndent()
            ToolType.OUTDENT -> setOutdent()
            ToolType.UNLINK -> setUnlink()
            ToolType.CLEAR -> clear()
            ToolType.EDIT_HTML -> editHtml()
            ToolType.NONE, ToolType.LINK, ToolType.TABLE, ToolType.TEXT_BACK_COLOR, ToolType.TEXT_FORE_COLOR -> {}
        }
    }

    protected fun exec(trigger: String) {
        if (isReady) {
            load(trigger)
        } else {
            postDelayed({ exec(trigger) }, 100)
        }
    }

    private fun load(trigger: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            evaluateJavascript(trigger, null)
        } else {
            loadUrl(trigger)
        }
    }

    private inner class RTextEditorWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            isReady = url.equals(ASSETS_EDITOR_HTML, ignoreCase = true)
            super.onPageFinished(view, url)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }
    }

    companion object {
        private const val ASSETS_EDITOR_HTML = "file:///android_asset/editor.html"
    }
}