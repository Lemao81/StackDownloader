package com.jueggs.stackdownloader.ui.search.delegate

abstract class AppModeDelegate<TDelegator> {
    fun initializeViews(delegator: TDelegator) = delegator.apply(initializeViewsInternal())

    open fun initializeViewsInternal(): TDelegator.() -> Unit = {}

    fun onInitialStart(delegator: TDelegator): TDelegator = delegator.apply(onInitialStartInternal())

    open fun onInitialStartInternal(): TDelegator.() -> Unit = {}

    fun setListeners(delegator: TDelegator): TDelegator = delegator.apply(setListenersInternal())

    open fun setListenersInternal(): TDelegator.() -> Unit = {}

    fun onBackPressed(delegator: TDelegator): TDelegator = delegator.apply(onBackPressedInternal())

    open fun onBackPressedInternal(): TDelegator.() -> Unit = {}
}