package com.wilderpereira.presenters

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.*
import com.wilderpereira.domain.Coordinate
import com.wilderpereira.utils.PropertiesUtils
import java.io.FileInputStream

class MainPresenter : MainContract.Presenter {

    private lateinit var view: MainContract.View
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private val FIREBASE_URL = "firebase.url"

    init {
        initializeFirebase()
    }

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun receiveCoordinates() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val coordinate = dataSnapshot.getValue(Coordinate::class.java)
                view.displayCoordinates(coordinate)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                view.displayError("The read failed: " + databaseError.code)
            }
        })
    }

    private fun initializeFirebase() {
        val serviceAccount = FileInputStream("serviceAccountKey.json")
        val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl(PropertiesUtils.readKey(FIREBASE_URL))
            .build()

        FirebaseApp.initializeApp(options)
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("7pid9ujoN2dZOSO3nxp8PkLSoHy2")
    }

}
