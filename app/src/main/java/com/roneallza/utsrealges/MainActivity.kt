package com.roneallza.utsrealges

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.roneallza.utsrealges.api.RetrofitClient
import com.roneallza.utsrealges.api.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var titleInput: EditText
    lateinit var slugInput: EditText
    lateinit var bodyInput: EditText
    lateinit var submitButton: Button
    lateinit var displayPostsButton: Button
    lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleInput = findViewById(R.id.titleInput)
        slugInput = findViewById(R.id.slugInput)
        bodyInput = findViewById(R.id.bodyInput)
        submitButton = findViewById(R.id.submitButton)
        displayPostsButton = findViewById(R.id.displayPostsButton)
        resultTextView = findViewById(R.id.resultTextView)

        submitButton.setOnClickListener {
            val title = titleInput.text.toString()
            val slug = slugInput.text.toString()
            val body = bodyInput.text.toString()

            RetrofitClient.postService.createPost(title, slug, body).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        resultTextView.text = "Post created successfully: ${response.body()?.title}"
                    } else {
                        resultTextView.text = "Failed to create post"
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    resultTextView.text = "Error: ${t.message}"
                }
            })
        }

        // Navigasi ke DisplayPostsActivity
            displayPostsButton.setOnClickListener {
                val intent = Intent(this, DisplayPostsActivity::class.java)
                startActivity(intent)
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
