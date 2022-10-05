package com.bignerdranch.android.pract11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.bignerdranch.android.pract11.data.DATABASE_NAME
import com.bignerdranch.android.pract11.data.KnigaDB
import com.bignerdranch.android.pract11.data.models.KnigaTypes
import com.bignerdranch.android.pract11.data.models.KnigaZhanr
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import java.util.concurrent.Executors

class zapomnit : AppCompatActivity() {

    private val Books: MutableList<KnigaTypes> = mutableListOf()
    private val Book: MutableList<KnigaZhanr> = mutableListOf()
    private lateinit var b1: Button
    private lateinit var db: KnigaDB
    private lateinit var aV: KnigaRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zapomnit)
        Log.d("ggggg", "asd")
        val index = intent.getIntExtra("number", -1)
        val toast = Toast.makeText(applicationContext, "Запомнили)", Toast.LENGTH_SHORT)
        b1 = findViewById(R.id.buttons)
        var editText = findViewById<EditText>(R.id.editTextTextPersonName)
        var editText1 = findViewById<EditText>(R.id.editTextTextPersonName3)
        var editText2 = findViewById<EditText>(R.id.editTextNumber)
        var editText3 = findViewById<EditText>(R.id.editTextTextPersonName2)
        val bb = findViewById<ImageButton>(R.id.imageButton)
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        val intent = Intent(this, razbor::class.java)

        db = Room.databaseBuilder(this, KnigaDB::class.java, DATABASE_NAME).build()

        val knigaDAO = db.knigaDAO()

        val executor = Executors.newSingleThreadExecutor()

        /*if (preferences.contains("json")) {
            val listBooks: List<Book> = Gson().fromJson<MutableList<Book>>(preferences.getString("json", "qwe").toString(), object : TypeToken<MutableList<Book>>() {}.type)
            Books.addAll(listBooks)
        }*/

        db.knigaDAO().getAllBooks().observe(this, androidx.lifecycle.Observer {
            Books.addAll(it)
        })


        if(index > -1) {
            db.knigaDAO().getAllZhanrs().observe(this) {
                Book.addAll(it)
                b1.setText("Изменить")
                editText.setText(Books[index].name)
                editText1.setText(Books[index].author)
                editText2.setText(Books[index].numPages)
                editText3.setText(Book[index].zhanr)
            }
        }



        b1.setOnClickListener{
            if (index == -1) {
                executor.execute{
                    knigaDAO.addBook(KnigaTypes(0, "${editText.text}", "${editText1.text}", "${editText2.text}"))
                    knigaDAO.addZhanr(KnigaZhanr(0, 0, "${editText3.text}", Date()))
                }


            }
            else if (index > -1){
                executor.execute{
                    knigaDAO.saveBook(KnigaTypes(index+1, "${editText.text}", "${editText1.text}", "${editText2.text}"))
                    knigaDAO.saveZhanr(KnigaZhanr(index+1,0,"${editText3.text}", Date()))
                    intent.putExtra("izmenilos", 1)
                }
                upInfo()
                super.onBackPressed()
            }

            toast.show()
        }

        bb.setOnClickListener{
            super.onBackPressed()
        }
    }

    fun upInfo(){
        Books.clear()
        db.knigaDAO().getAllBooks().observe(this, androidx.lifecycle.Observer {
            Books.addAll(it)
        })
    }



}

