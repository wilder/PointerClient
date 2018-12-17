package com.wilderpereira

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.*
import com.wilderpereira.utils.PropertiesUtils
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import java.awt.GraphicsEnvironment
import java.io.FileInputStream
import com.wilderpereira.domain.Coordinate


class Application : javafx.application.Application() {

    private val FIREBASE_URL = "firebase.url"

    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference

    override fun start(primaryStage: Stage) {
        setupFirebase()
        setupStage(primaryStage)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val coordinate = dataSnapshot.getValue(Coordinate::class.java)
                println("Coordinate: ${coordinate}")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Nao")
                println("The read failed: " + databaseError.code)
            }
        })

    }

    private fun setupFirebase() {
        val serviceAccount = FileInputStream("serviceAccountKey.json")
        val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl(PropertiesUtils.readKey(FIREBASE_URL))
            .build()

        FirebaseApp.initializeApp(options)
        database = FirebaseDatabase.getInstance()
        ref = database.getReference("7pid9ujoN2dZOSO3nxp8PkLSoHy2")
    }

    private fun setupStage(primaryStage: Stage) {
        val root = Group()
        primaryStage.initStyle(StageStyle.TRANSPARENT)
        val maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().maximumWindowBounds

        val canvas = Canvas(maximumWindowBounds.getWidth(), maximumWindowBounds.getHeight())
        val gc = canvas.graphicsContext2D

        drawShapes(gc)
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
