package com.example.composelearning

/**
 * Created by Sagar Pujari on 23/10/22.
 */
class House private constructor(builder: Builder) {
    private var height : Int = 0
    private var width : Int = 0
    private var color : String? = null

    init {
        if (height == 0) throw Exception("Height can't be 0") else this.height = builder.height
        this.width = builder.width
        this.color = builder.color
    }

    class Builder {
        var height : Int = 0
        private set
        var width : Int = 0
        private set
        var color : String? = null
        private set

        fun setHeight(height: Int): Builder {
            this.height = height
            return this
        }

        fun setWidth(width: Int): Builder {
            this.width = width
            return this
        }

        fun setColor(color: String): Builder {
            this.color = color
            return this
        }

        fun build():House {
            return House(this)
        }
    }
}