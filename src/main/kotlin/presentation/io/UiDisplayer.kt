package org.example.presentation.io

import org.example.presentation.action.MenuAction


interface UiDisplayer {
    fun displayMenu(options:List<MenuAction>)
    fun displayMessage(message: String)
    fun displayError(message: String?)
    fun displayPrompt(prompt: String)
}


