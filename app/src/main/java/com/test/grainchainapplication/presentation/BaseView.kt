package com.test.grainchainapplication.presentation

/**
 * Created by Luis Vargas on 3/19/19.
 */

interface BaseView<in T : BasePresenter> {

    /**
     * Set presenter
     */
    fun setPresenter(presenter: T)

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    fun showLoading()

    /**
     * Hide a loading view.
     */
    fun hideLoading()

    /**
     * Show a message
     *
     * @param message A string representing an error.
     */
    fun showMessage(message: String)


}