package com.wilderpereira.presenters

import com.wilderpereira.domain.Coordinate

interface MainContract {
    interface Presenter {
        fun receiveCoordinates()
        fun bindView(view: View)
    }

    interface View {
        fun displayCoordinates(coordinate: Coordinate)
        fun displayError(message: String)
    }
}
