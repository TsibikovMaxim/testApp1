package com.example.testalef

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testalef.Adapters.PhotosRvAdapter
import com.example.testalef.Retrofit.API
import com.example.testalef.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import android.content.Intent





class PhotosActivity : AppCompatActivity(), PhotosRvAdapter.onItemClickListener {

    internal lateinit var jsonApi: API
    private var recyclerView : RecyclerView ?= null
    private var gridLayoutManager: GridLayoutManager ?= null
    private var photosRvAdapter: PhotosRvAdapter ?= null
    private var list: List<String> ?= null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        val actionbar = supportActionBar
        actionbar!!.title =  "Фотографии"

        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(API::class.java)

        jsonApi.photos.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::HandleSuccess, this::HanleError)

        val swipeRefreshLayout =findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            photosRvAdapter?.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }

    }
    private fun HandleSuccess(photosList: List<String>) {
        list = photosList
        showRecycler(list!!)
    }

    private fun HanleError(error: Throwable) {
        Toast.makeText(this, "Error, something went wrong", Toast.LENGTH_SHORT).show()
    }

    private fun showRecycler(list: List<String>) {
        recyclerView = findViewById(R.id.recyclerView)
        gridLayoutManager = GridLayoutManager(applicationContext,2,
            LinearLayoutManager.VERTICAL,false)
        recyclerView?.layoutManager = gridLayoutManager
        photosRvAdapter = PhotosRvAdapter(applicationContext, list!!,this@PhotosActivity)
        recyclerView?.adapter = photosRvAdapter
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@PhotosActivity, ShowPhotoActivity::class.java)
        intent.putExtra("photoUrl",list!![position])
        startActivity(intent)
    }
}