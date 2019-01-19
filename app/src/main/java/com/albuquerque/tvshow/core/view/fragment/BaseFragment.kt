package com.albuquerque.tvshow.core.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import org.greenrobot.eventbus.EventBus

abstract class BaseFragment: Fragment() {

    private val bus by lazy { EventBus.getDefault() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.invalidateOptionsMenu()
        registerEventBus()
    }

    override fun onStart() {
        super.onStart()
        registerEventBus()
    }

    override fun onStop() {
        super.onStop()
        unregisterEventBus()
    }

    private fun registerEventBus() {
        if (!bus.isRegistered(this)) {
            bus.register(this)
        }
    }

    private fun unregisterEventBus() {
        if (bus.isRegistered(this)) {
            bus.unregister(this)
        }
    }

}