package com.ravzanurcanturk.aboutfood

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ravzanurcanturk.aboutfood.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotesBinding
    private lateinit var db : FirebaseFirestore

    private val notesList = mutableListOf<String>()
    var tempName :String  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = Firebase.firestore

        val intent = intent
        tempName = intent.getStringExtra("names").toString()

        binding.editNotesButton.setOnClickListener { onSaveButtonClick() }

    }

    private fun onSaveButtonClick() {
        val note = binding.editNotesText.text.toString()

        /*val data = hashMapOf("notes" to note)

        db.collection("cities").document("BJ")
            .set(data, SetOptions.merge())

        db.collection("MyProducts").whereEqualTo("name", tempName)*/



        if (note.isNotEmpty()) {
            notesList.add(note)
            displayNotes()
            clearNoteInput()
        }
    }

    private fun displayNotes() {
        val notesText = notesList.joinToString("\n")
        binding.notesText.text = notesText
    }

    private fun clearNoteInput() {
        binding.editNotesText.text.clear()
    }
}