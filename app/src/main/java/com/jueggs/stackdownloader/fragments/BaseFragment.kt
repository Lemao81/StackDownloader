package com.jueggs.stackdownloader.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jueggs.stackdownloader.dagger.ApplicationComponent
import com.jueggs.stackdownloader.utils.dagger

abstract class BaseFragment : Fragment() {
    lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies(dagger())
    }

    abstract fun injectDependencies(component: ApplicationComponent)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(getLayout(), container, false)

    abstract fun getLayout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializePresenter()
        initializeComponents()
        initializeListeners()
    }

    abstract fun initializePresenter()
    open fun initializeComponents() {}
    open fun initializeListeners() {}

    override fun onStart() {
        super.onStart()
        checkNotNull(context)
        ctx = context!!
    }

    override fun onStop() {
        super.onStop()
        ctx = context!!.applicationContext
    }
}