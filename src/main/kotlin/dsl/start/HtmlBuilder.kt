package dsl.start

fun main() {
    val page = html {
        body {
            h1 {
                style("{ font-weight: bold }")
                hidden(false)

                +"This is an example of a DSL"
            }
            div {
                p {
                    hidden(true)
                    +"This is where we could add some conent."
                    +"Maybe some more"
                }
            }
        }
    }

    println(page.dump())
}

abstract class HtmlNode(val name: String) {
    val children = mutableListOf<HtmlNode>();
    val attrs = mutableListOf<Pair<String, String>>();

    open fun dump(prefix: String = ""): String {
        val dumpedChildren = children.map { it.dump(prefix + "    ") }.joinToString("")
        val attrs = if (attrs.any())
            " " + attrs.map { "${it.first}=\"${it.second}\"" }.joinToString(" ")
        else
            ""

        return "$prefix<$name$attrs>\n$dumpedChildren$prefix</$name>\n"
    }
}

class StringNode(val content: String) : HtmlNode("") {
    override fun dump(prefix: String): String {
        return prefix + content + "\n"
    }
}

class Html : HtmlNode("html") {
    fun body(builder: BodyElement.() -> Unit): BodyElement {
        val element = BodyElement("body").apply(builder)
        children.add(element)
        return element
    }
}

class BodyElement(name: String) : HtmlNode(name) {
    private fun addTag(name: String, builder: BodyElement.() -> Unit): BodyElement {
        val element = BodyElement(name).apply(builder)
        children.add(element)
        return element
    }

    fun div(builder: BodyElement.() -> Unit): BodyElement {
        return addTag("div", builder)
    }

    fun p(builder: BodyElement.() -> Unit): BodyElement {
        return addTag("p", builder)
    }

    fun h1(builder: BodyElement.() -> Unit): BodyElement {
        return addTag("h1", builder)
    }

    operator fun String.unaryPlus(): Unit {
        children.add(StringNode(this))
    }

    fun style(content: String): Unit {
        attrs.add(Pair("style", content))
    }

    fun hidden(hidden: Boolean): Unit {
        attrs.add(Pair("hidden", hidden.toString()))
    }

    fun className(className: String): Unit {
        attrs.add(Pair("hidden", className))
    }
}

fun html(builder: Html.() -> Unit): Html {
    return Html().apply(builder)
}
