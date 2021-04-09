package com.googleanalytics

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

object DataProviderManager {

    //Keys Intent with layouts.
    const val LAYOUT_KEY: String = "lay_id"
    const val FOOD: String = "Food"
    const val CLOTHES: String = "Clothes"
    const val ELECTRONIC: String = "Electronic"

    // Times
    var startTime: Long = 0
    var endTime: Long = 0


    fun spentTime(activity: Activity,activityName:String, starTime: Long) {
        endTime = System.currentTimeMillis()
        val diff: Long = endTime - starTime
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
//        val days = hours / 24


        saveDateOnFireStore(activity,activityName, seconds, minutes, hours)
        Log.e("Time", "seconds: $seconds , minutes: $minutes , hours: $hours ")

    }


    private fun saveDateOnFireStore(
        activity: Activity,
        activityName:String,
        seconds: Long,
        minutes: Long,
        hours: Long
    ) {
        val user: MutableMap<String, Any> = HashMap()
        val db = FirebaseFirestore.getInstance()
        user["ActivityName"] = activityName
        user["seconds"] = seconds
        user["minutes"] = minutes
        user["hours"] = hours

        for (key in user.keys) {
            println("Element at key $key = ${user[key]}")
        }

        if (!user.values.isEmpty()) {
            // Add a new document with a generated ID
            db.collection("TimeSpentUser")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(
                        "TAG",
                        "DocumentSnapshot added with ID: " + documentReference.id
                    )
                    Toast.makeText(activity, "Success Added", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener { e -> Log.w("TAG", "Error adding document", e) }
        } else {
            Toast.makeText(activity, "Enter Data", Toast.LENGTH_SHORT).show()
        }
    }


}
