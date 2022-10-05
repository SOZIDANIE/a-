package com.bignerdranch.android.pract11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bignerdranch.android.pract11.data.DATABASE_NAME
import com.bignerdranch.android.pract11.data.KnigaDB
import com.bignerdranch.android.pract11.data.models.KnigaTypes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.Executors

class spisok : AppCompatActivity() {

    private val Books: MutableList<KnigaTypes> = mutableListOf()
    private lateinit var rv: RecyclerView
    private lateinit var db: KnigaDB
    private var ic: Int = -1
    private var pV: Int = -1
    private lateinit var aV: KnigaRVAdapter
    var index = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spisok)
        rv = findViewById(R.id.recycler)
        val bb2 = findViewById<ImageButton>(R.id.i2)
        val adapter = KnigaRVAdapter(this, Books)

        db = Room.databaseBuilder(this, KnigaDB::class.java, DATABASE_NAME).build()
        val knigaDAO = db!!.knigaDAO()

        getInfo()

        /*
        val recListener = object : KnigaRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                index = position
                val intent = Intent(this@spisok, zapomnit::class.java)
                intent.putExtra("number", position)
                startActivity(intent)
            }
        }

         */
        //adapter.setClickListener(recListener)

        //rv.layoutManager = LinearLayoutManager(this)
        //rv.adapter = adapter

        bb2.setOnClickListener{
            super.onBackPressed()
        }
    }


    override fun onResume() {
        super.onResume()

        Log.d("asd", "wgfdtukyukyuyk $index " )

        if (index != -1){
            upInfo()
            rv.adapter?.notifyItemChanged(index)
        }
    }

    private fun upInfo(){
        Books.clear()
        db.knigaDAO().getAllBooks().observe(this, androidx.lifecycle.Observer {
            //Books.addAll(it)
            if(Books.isEmpty()) getInfo()
        })
    }

    private fun getInfo(){
        /*
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String= ""
        if (preferences.contains("json")){
            json = preferences.getString("json", "NOT_JSON").toString()
        }else{
            return
        }
        val tempList = Gson().fromJson<List<Book>>(json, object: TypeToken<List<Book>>(){}.type)
        Books.addAll(tempList)
         */
        val rvListner = object  : KnigaRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int){
                pV = position
                val intent = Intent(this@spisok, razbor::class.java)
                intent.putExtra("number", position)
                index = position
                startActivity(intent)
            }
        }
        aV = KnigaRVAdapter(this, Books)
        aV.setClickListener(rvListner)
        db.knigaDAO().getAllBooks().observe(this, androidx.lifecycle.Observer {
            Books.addAll(it)
            rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rv.adapter = aV
        })

    }

}