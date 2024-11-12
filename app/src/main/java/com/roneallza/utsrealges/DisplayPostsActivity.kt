package com.roneallza.utsrealges

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.roneallza.utsrealges.api.RetrofitClient
import com.roneallza.utsrealges.api.model.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayPostsActivity : AppCompatActivity() {

    lateinit var postsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_posts)

        postsTextView = findViewById(R.id.postsTextView)

        // Memanggil API untuk mendapatkan semua post
        RetrofitClient.postService.getPosts().enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.isSuccessful) {
                    val posts = response.body()?.data
                    val result = StringBuilder()

                    posts?.forEach { post ->
                        result.append("ID: ${post.id}\n")
                        result.append("Title: ${post.title}\n")
                        result.append("Slug: ${post.slug}\n")
                        result.append("Body: ${post.body}\n")
                        result.append("Created At: ${post.created_at}\n")
                        result.append("Updated At: ${post.updated_at}\n")
                        result.append("\n------------------\n\n")
                    }

                    postsTextView.text = result.toString()
                } else {
                    postsTextView.text = "Failed to retrieve posts"
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                postsTextView.text = "Error: ${t.message}"
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.display_post)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}