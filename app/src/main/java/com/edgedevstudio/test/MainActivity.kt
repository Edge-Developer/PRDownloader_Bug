package com.edgedevstudio.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.downloader.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PRDownloader.initialize(getApplicationContext())
        val url = "http://www.alexszepietowski.com/wp-content/uploads/downloads/2013/03/The 2 Golden Rules of Property, Business and Life!.pdf"
        val fileName = "MyFile.pdf"
        val dirPath = "/File Downloader/PDF/$fileName"

        val downloadId = PRDownloader.download(url, dirPath, fileName)
                .build()
                .setOnStartOrResumeListener {  Log("OnStartOrResumeListener. onStartOrResume")}
                .setOnPauseListener { }
                .setOnCancelListener{ Log("OnCancelListener. onCancel")}

                .setOnProgressListener {
                    Log("Progress = ${((it.currentBytes / it.totalBytes) * 100)}%")
                }
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        Log("OnDownloadListener. onDownloadComplete")
                    }

                    override fun onError(error: Error) {
                        Log("onError. isConnectionError = ${error.isConnectionError}, isServerError = ${error.isServerError}")

                    }
                })
        Log("onCreate, DownloadId = $downloadId")
    }
    fun Log(msg : String){
        Log.d("MainActivity", msg)
    }
}
