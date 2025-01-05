package com.example.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var savedText : TextView
        private lateinit var editText : TextInputEditText
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        editText = findViewById(R.id.text_input_edit_text)
        savedText = findViewById(R.id.tv_2)
        button =findViewById(R.id.button)

        getSavedData()

        button.setOnClickListener {
            val storedText : String = editText.text.toString()
            saveData(storedText)
        }
    }

    private fun saveData(storedText: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("name", MODE_PRIVATE)
        val saveText: SharedPreferences.Editor = sharedPreferences.edit()
        saveText.putString("name", storedText).apply()
    }

    private fun getSavedData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("name", MODE_PRIVATE)
        val text: String? = sharedPreferences.getString("name", "")
        savedText.text = text
    }
}