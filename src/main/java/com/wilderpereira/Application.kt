package com.wilderpereira

import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import java.awt.GraphicsEnvironment
import com.wilderpereira.domain.Coordinate
import com.wilderpereira.presenters.MainContract
import com.wilderpereira.presenters.MainPresenter
import com.wilderpereira.domain.lowPassFilter


class Application : javafx.application.Application(), MainContract.View {

    private lateinit var graphicsContext: GraphicsContext
    private lateinit var canvas: Canvas
    private lateinit var presenter: MainContract.Presenter
    private var accelerometerValues: Coordinate? = null

    override fun start(primaryStage: Stage) {
        presenter = MainPresenter()
        presenter.bindView(this)
        setupStage(primaryStage)
        presenter.receiveCoordinates()
    }

    override fun displayPoint(coordinate: Coordinate) {
        // TODO: move to presenter or coordinate manager
        accelerometerValues = lowPassFilter(coordinate, accelerometerValues)
        val x = canvas.width/2 - accelerometerValues!!.x*canvas.width/12
        val y = canvas.height/2 - accelerometerValues!!.y*canvas.height/12

        clearScreen()
        graphicsContext.fill = Color.MEDIUMSLATEBLUE
        graphicsContext.fillOval(x, y, 75.toDouble(), 75.toDouble())

        //TODO: clear everything after - no need to listen for stop event
    }

    override fun focus(coordinate: Coordinate) {
        // TODO: move to presenter or coordinate manager
        accelerometerValues = lowPassFilter(coordinate, accelerometerValues)
        val x = canvas.width/2 - accelerometerValues!!.x*canvas.width/12
        val y = canvas.height/2 - accelerometerValues!!.y*canvas.height/12

        addShadowMask()
        graphicsContext.clearRect(x, y, 95.toDouble(), 95.toDouble())
    }

    private fun addShadowMask() {
        graphicsContext.fill = Color.web("#00000090")
        fillWholeScreen()
    }

    private fun fillWholeScreen() {
        clearScreen()
        graphicsContext.fillRect(0.toDouble(), 0.toDouble(), canvas.width, canvas.height)
    }

    override fun displayError(message: String) {
        println(message)
    }

    override fun clearScreen() {
        graphicsContext.clearRect(0.toDouble(), 0.toDouble(), canvas.width, canvas.height)
    }

    private fun setupStage(primaryStage: Stage) {
        val root = Group()
        primaryStage.initStyle(StageStyle.TRANSPARENT)
        val maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().maximumWindowBounds

        canvas = Canvas(maximumWindowBounds.getWidth(), maximumWindowBounds.getHeight())
        graphicsContext = canvas.graphicsContext2D

        root.children.add(canvas)

        primaryStage.scene = Scene(root)
        primaryStage.scene.fill = Color.TRANSPARENT
        primaryStage.show()
    }

}
