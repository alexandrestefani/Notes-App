package com.alexandrestefani.noteideas.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexandrestefani.noteideas.Adapter.ListaNotesAdapter
import com.alexandrestefani.noteideas.DataClass.AppDatabase
import com.alexandrestefani.noteideas.R
import com.alexandrestefani.noteideas.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapterNotes: ListaNotesAdapter

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val notes_Dao by lazy {
        val db = AppDatabase.instance(this)
        db.notesDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var userId = intent.getStringExtra("USER_ID")

        configWelcomeText(userId)
        configFloatingButton(userId)
        adapterconfig()
    }

    private fun configWelcomeText(userId: String?) {
        binding.textArrived.setText("Ola, $userId \nsuas anotaçoes")
    }

    private fun configFloatingButton(userId: String?) {
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, DetailsNote::class.java)
            intent.putExtra("USER_ID", userId)
            Log.i("usuario", "usuário $userId")
            startActivity(intent)
        }
    }

    private fun adapterconfig() {
        adapterNotes = ListaNotesAdapter(this)
        recyclerView = binding.recyclerviewNotes
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterNotes

        adapterNotes.whenClickonItem = {
            val intent2 = Intent(
                this, DetailsNote::class.java
            ).apply {
                putExtra("chave", it)
            }
            startActivity(intent2)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_signout -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            var userId = intent.getStringExtra("USER_ID")
            val note = notes_Dao.getAllFromThisId(userId)
            adapterNotes.refresh(note)
        }
    }
}