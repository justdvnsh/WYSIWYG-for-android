//package divyansh.tech.wysiwyg.extentions
//
//import divyansh.tech.wysiwyg.utils.HtmlTag
//
//class HTMLExtensions(editorCore: EditorCore) {
//    var editorCore: EditorCore = editorCore
//    fun getStyleMap(element: Element): Map<String, String> {
//        val keymaps: MutableMap<String, String> = HashMap()
//        if (!element.hasAttr("style")) {
//            return keymaps
//        }
//        val styleStr: String =
//            element.attr("style") // => margin-top:10px;color:#fcc;border-bottom:1px solid #ccc; background-color: #333; text-align:center
//        val keys = styleStr.split(":".toRegex()).toTypedArray()
//        var split: Array<String>
//        if (keys.size > 1) {
//            for (i in keys.indices) {
//                if (i % 2 != 0) {
//                    split = keys[i].split(";".toRegex()).toTypedArray()
//                    if (split.size == 1) break
//                    keymaps[split[1].trim { it <= ' ' }] =
//                        keys[i + 1].split(";".toRegex()).toTypedArray()[0].trim { it <= ' ' }
//                } else {
//                    split = keys[i].split(";".toRegex()).toTypedArray()
//                    if (i + 1 == keys.size) break
//                    keymaps[keys[i].split(";".toRegex())
//                        .toTypedArray()[split.size - 1].trim { it <= ' ' }] =
//                        keys[i + 1].split(";".toRegex()).toTypedArray()[0].trim { it <= ' ' }
//                }
//            }
//        }
//        return keymaps
//    }
//
//    fun getHtmlSpan(element: Element): String {
//        val el = Element(Tag.valueOf("span"), "")
//        el.attributes().put("style", element.attr("style"))
//        el.html(element.html())
//        return el.toString()
//    }
//
//    private fun hasChildren(element: Element): Boolean {
//        return element.getAllElements().size() > 0
//    }
//
//    fun getTemplateHtml(child: EditorType): String? {
//        var template: String? = null
//        when (child) {
//            INPUT -> template =
//                "<{{\$tag}} data-tag=\"input\" {{\$style}}>{{\$content}}</{{\$tag}}>"
//            HtmlTag.hr -> template = "<hr data-tag=\"hr\"/>"
//            HtmlTag.ul -> template = "<ul data-tag=\"ul\">{{\$content}}</ul>"
//            OL_LI, UL_LI -> {
//                val dataTag =
//                    if (child === EditorType.OL_LI) "data-tag=\"list-item-ol\"" else "data-tag=\"list-item-ul\""
//                template = "<li $dataTag><{{\$tag}} {{\$style}}>{{\$content}}</{{\$tag}}></li>"
//            }
//        }
//        return template
//    }
//
//    companion object {
//        fun matchesTag(test: String?): Boolean {
//            for (tag in HtmlTag.values()) {
//                if (tag.name == test) {
//                    return true
//                }
//            }
//            return false
//        }
//    }
//
//}