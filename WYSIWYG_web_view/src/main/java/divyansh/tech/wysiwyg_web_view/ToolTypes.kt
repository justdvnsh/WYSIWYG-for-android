package divyansh.tech.wysiwyg_web_view

annotation class ToolType {
    companion object {
        var NONE = 0
        var BOLD = 1
        var ITALIC = 2
        var UNDERLINE = 3
        var STRIKETHROUGH = 4
        var REMOVE_FORMAT = 5
        var NORMAL = 6
        var H1 = 7
        var H2 = 8
        var H3 = 9
        var H4 = 10
        var H5 = 11
        var H6 = 12
        var SUPERSCRIPT = 13
        var SUBSCRIPT = 14
        var TEXT_FORE_COLOR = 15
        var TEXT_BACK_COLOR = 16
        var CODE = 17
        var UNORDERED_LIST = 18
        var ORDERED_LIST = 19
        var QUOTE = 20
        var ALIGN_LEFT = 21
        var ALIGN_CENTER = 22
        var ALIGN_RIGHT = 23
        var ALIGN_JUSTIFY = 24
        var HORIZONTAL_RULE = 25
        var INDENT = 26
        var OUTDENT = 27
        var TABLE = 28
        var LINK = 29
        var UNLINK = 30
        var CLEAR = 31
        var EDIT_HTML = 32
    }
}