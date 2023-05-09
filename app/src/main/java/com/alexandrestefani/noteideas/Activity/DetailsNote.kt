package com.alexandrestefani.noteideas.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.alexandrestefani.noteideas.DataClass.AppDatabase
import com.alexandrestefani.noteideas.Model.Notes
import com.alexandrestefani.noteideas.databinding.ActivityDetailsBinding
import kotlinx.coroutines.launch

class DetailsNote : AppCompatActivity() {

    private val binding by lazy{
        ActivityDetailsBinding.inflate(layoutInflater)
    }
    private val notesDaoDatabase by lazy {
        AppDatabase.instance(this).notesDao()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //save Datas
        var datas = intent.getParcelableExtra<Notes>("chave")
        var idNotes = datas?.id
        var iduser = if(datas?.usuarioId != null){datas?.usuarioId
            }else {"A"}

        var userId = intent.getStringExtra("USER_ID")
        var userIdShure = if(userId != null){userId} else{"A"}

        binding.killButton.setOnClickListener {
            if(idNotes != null){
                var note = Notes(
                    idNotes,
                    binding.titleDetails.text.toString(),
                    binding.textDetails.text.toString(),
                   userIdShure
                )
                lifecycleScope.launch {
                    notesDaoDatabase.delete(note)
                    finish()
                }

                }else {
                    Toast.makeText(this, "Somente deleta-se itens criados", Toast.LENGTH_LONG).show()
            }
        }

        binding.imageSave.setOnClickListener {
            if (idNotes != null) {
                var note = Notes(
                    idNotes,
                    binding.titleDetails.text.toString(),
                    binding.textDetails.text.toString(),
                    iduser
                )
                lifecycleScope.launch {
                    notesDaoDatabase.update(note)
                    finish()
                }

            } else {
                var note = Notes(
                    0L,
                    binding.titleDetails.text.toString(),
                    binding.textDetails.text.toString(),
                    userIdShure
                )
                lifecycleScope.launch {
                    notesDaoDatabase.save(note)
                    finish()
                }
            }
        }


        //Show Datas in View
        binding.titleDetails.setText(datas?.titleNotes)
        binding.textDetails.setText(datas?.textNotesval)
    }

}