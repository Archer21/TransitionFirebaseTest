package com.archer.transitionfirebasetest.common;

/**
 * Created by archer on 04-01-17.
 */

public abstract class BasePresenter {
    /**
     * Viewmodel methods to initialize (i.e: setupList, setupAdapter)
     */
    public abstract void onStart ();


    /**
     * Used to make heavy operations. (i.e: call the presenter and
     * make an http call to fill the adapter)
     */
    public abstract void onResume ();

    /**
     * Free the reference to the view and unused variables to avoid
     * memory leaks
     */
    public abstract void onStop ();
}



































