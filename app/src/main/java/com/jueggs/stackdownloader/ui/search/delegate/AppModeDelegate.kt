package com.jueggs.stackdownloader.ui.search.delegate

interface AppModeDelegate<in TDelegator> {
    fun setListeners(delegator: TDelegator)

    fun onBackPressed(delegator: TDelegator)
}