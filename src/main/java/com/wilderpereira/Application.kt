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


class Application : javafx.application.Application(), MainContract.View {

    private lateinit var graphicsContext: GraphicsContext
    private lateinit var presenter: MainContract.Presenter

    override fun start(primaryStage: Stage) {
        presenter = MainPresenter()
        presenter.bindView(this)
        setupStage(primaryStage)
        presenter.receiveCoordinates()
    }

    override fun displayCoordinates(coordinate: Coordinate) {
        println("Coordinate: $coordinate")
    }

    override fun displayError(message: String) {
        println(message)
    }

    private fun setupStage(primaryStage: Stage) {
        val root = Group()
        primaryStage.initStyle(StageStyle.TRANSPARENT)
        val maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().maximumWindowBounds

        val canvas = Canvas(maximumWindowBounds.getWidth(), maximumWindowBounds.getHeight())
        graphicsContext = canvas.graphicsContext2D

        drawShapes(graphicsContext)
        root.children.add(canvas)

        primaryStage.scene = Scene(root)
        primaryStage.scene.fill = Color.TRANSPARENT
        primaryStage.show()
    }

    private fun drawShapes(graphicsContext: GraphicsContext) {
        graphicsContext.fill = Color.MEDIUMSLATEBLUE;
        graphicsContext.stroke = Color.BLUE;

        graphicsContext.fillOval(0.toDouble(), 0.toDouble(), 30.toDouble(), 30.toDouble())
        graphicsContext.strokeOval(60.toDouble(), 60.toDouble(), 30.toDouble(), 30.toDouble());
    }

}
