package com.example.noteapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.ItemListBinding

typealias OnClickNote = (Note) -> Unit

class NoteAdapter (private val listNote: List<Note>): RecyclerView.Adapter<NoteAdapter.ItemNoteViewHolder>(){
    inner class ItemNoteViewHolder(
        private val binding: ItemListBinding
    ):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: Note){
            with(binding){
                titleTxt.text = data.title
                descTxt.text = data.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNoteViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context))
        return ItemNoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: ItemNoteViewHolder, position: Int) {
        holder.bind(listNote[position])
    }
}