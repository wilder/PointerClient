package com.wilderpereira.presenters

import com.wilderpereira.domain.Coordinate

interface MainContract {
    interface Presenter {
        fun receiveCoordinates()
        fun bindView(view: View)
    }

    interface View {
        fun displayError(message: String)
        fun displayPoint(coordinate: Coordinate)
        fun focus(coordinate: Coordinate)
        fun clearScreen()
    }
}
