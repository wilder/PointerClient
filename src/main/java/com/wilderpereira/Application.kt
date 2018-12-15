package com.wilderpereira

import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class Application : javafx.application.Application() {

    override fun start(primaryStage: Stage) {
        val btn = Button()
        btn.text = "Say 'Hello World'"
        btn.setOnAction { event -> println("Hello World!") }

        val root = StackPane()
        root.children.add(btn)

        val scene = Scene(root, 300.0, 250.0)

        primaryStage.title = "Hello World!"
        primaryStage.scene = scene
        primaryStage.show()
    }

}
