package com.jueggs.stackdownloader.render

interface Renderer<T> {
    fun render(model: T)
}