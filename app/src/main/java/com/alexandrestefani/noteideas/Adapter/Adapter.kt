package com.alexandrestefani.noteideas.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexandrestefani.noteideas.Model.Notes
import com.alexandrestefani.noteideas.databinding.CardBinding

class ListaNotesAdapter(
    private val context: Context,
    produtos: List<Notes> = emptyList(),
    var whenClickonItem: (produto: Notes) -> Unit = {}
) : RecyclerView.Adapter<ListaNotesAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: CardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var produto: Notes
        init {
            itemView.setOnClickListener {
                if (::produto.isInitialized) {
                    whenClickonItem(produto)
                }
            }
        }
        fun vincula(produto: Notes) {
            this.produto = produto
            val title = binding.titleCard
            title.text = produto.titleNotes
            val descricao = binding.textCard
            descricao.text = produto.textNotesval
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = CardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun refresh(produtos: List<Notes>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
