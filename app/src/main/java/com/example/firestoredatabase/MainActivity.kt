package com.example.firestoredatabase

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MainActivity : AppCompatActivity() {

    var firstName: EditText? = null
    var lastName:EditText? = null
    var age:EditText? = null
    private var Registerbtn: Button? = null
    var db: FirebaseFirestore? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseFirestore.getInstance()
        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        age = findViewById(R.id.age)
        Registerbtn = findViewById(R.id.btnRegister)
        Registerbtn?.setOnClickListener(View.OnClickListener {
            val Firstname = firstName?.getText().toString()
            val Lastname = lastName?.getText().toString()
            val Age = age?.getText().toString()
            val user: MutableMap<String, Any> = HashMap()
            user["First Name"] = Firstname
            user["Last Name"] = Lastname
            user["Age"] = Age
            db!!.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this@MainActivity, "Successful", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                }
        })

    }

}
