package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.noteapp.databinding.ActivityEditBinding
import com.example.noteapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class EditActivity : AppCompatActivity() {
    private lateinit var binding : ActivityEditBinding
    private var firestore = FirebaseFirestore.getInstance()
    private var noteCollectionRef = firestore.collection("note")
    private val noteListLiveData: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)

        setContentView(binding.root)

        with(binding){
            saveBtn.setOnClickListener {
                val title = titleEdt.text.toString()
                val desc = descEdt.text.toString()

                if(title.isNotEmpty() && desc.isNotEmpty()){
                    val note = hashMapOf(
                        "title" to title,
                        "description" to desc
                    )

                    noteCollectionRef.add(note)
                        .addOnSuccessListener { documentReference ->
                            Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                            val intentToMain = Intent(this@EditActivity, MainActivity::class.java)
                            startActivity(intentToMain)
                        }
                        .addOnFailureListener { e ->
                            Log.w("TAG", "Error adding document", e)
                        }
                } else {
                    Toast.makeText(this@EditActivity, "Please enter all the fields", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}