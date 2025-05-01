package com.linkfetch.downloader

import android.app.DownloadManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.os.IBinder

class DownloadService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val url = intent?.getStringExtra("DOWNLOAD_URL") ?: return START_NOT_STICKY
        downloadFile(url)
        return START_STICKY
    }

    private fun downloadFile(url: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("LinkFetch Download")
            .setDescription("Downloading...")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "LinkFetch_${System.currentTimeMillis()}"
            )
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }
}
