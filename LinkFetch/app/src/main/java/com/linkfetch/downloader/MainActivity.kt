package com.linkfetch.downloader

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDownload = findViewById<Button>(R.id.btn_download)
        val etLink = findViewById<EditText>(R.id.et_link)

        btnDownload.setOnClickListener {
            val url = etLink.text.toString().trim()
            if (url.isNotEmpty()) {
                startDownloadService(url)
            }
        }
    }

    private fun startDownloadService(url: String) {
        val intent = Intent(this, DownloadService::class.java)
        intent.putExtra("DOWNLOAD_URL", url)
        startService(intent)
    }
}
