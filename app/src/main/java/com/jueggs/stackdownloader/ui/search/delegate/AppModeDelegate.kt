package com.jueggs.stackdownloader.ui.search.delegate

interface AppModeDelegate<in TDelegator> {
    fun onInitialStart(delegator: TDelegator)

    fun setListeners(delegator: TDelegator)

    fun onBackPressed(delegator: TDelegator)
}