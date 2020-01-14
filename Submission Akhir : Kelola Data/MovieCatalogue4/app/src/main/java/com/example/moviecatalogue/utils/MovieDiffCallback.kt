package com.example.moviecatalogue.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity


class MovieDiffCallback(private val mOldNoteList: List<DataMoviesEntity>, private val mNewNoteList: List<DataMoviesEntity>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id === mNewNoteList.get(newItemPosition).id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteList[newItemPosition]

        return oldEmployee.original_title.equals(newEmployee.original_title)
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}