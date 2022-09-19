package com.corneliudascalu.bakerjourney.entry

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.corneliudascalu.bakerjourney.Ingredient
import com.corneliudascalu.bakerjourney.R
import com.corneliudascalu.bakerjourney.databinding.ListItemLogEntryDetailsImageBinding
import com.corneliudascalu.bakerjourney.databinding.ListItemLogEntryDetailsTextBinding

@Retention(AnnotationRetention.SOURCE)
@IntDef(TEXT, ADD_INGREDIENT, IMAGE)
private annotation class ItemType

const val TEXT = 1
const val ADD_INGREDIENT = 2
const val IMAGE = 3

class LogEntryAdapter : ListAdapter<LogEntryDetail, LogEntryDetailViewHolder<LogEntryDetail>>(DiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogEntryDetailViewHolder<LogEntryDetail> {
        return when (viewType) {
            TEXT -> LogEntryDetailViewHolder.TextVH(parent)
            ADD_INGREDIENT -> LogEntryDetailViewHolder.AddIngredientVH(parent)
            IMAGE -> LogEntryDetailViewHolder.ImageVH(parent)
            else -> throw IllegalArgumentException("Unknown item type $viewType")
        } as LogEntryDetailViewHolder<LogEntryDetail>
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType
    }

    override fun onBindViewHolder(holder: LogEntryDetailViewHolder<LogEntryDetail>, position: Int) {
        holder.bind(getItem(position))
    }

    private object DiffItemCallback : DiffUtil.ItemCallback<LogEntryDetail>() {
        override fun areItemsTheSame(oldItem: LogEntryDetail, newItem: LogEntryDetail): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LogEntryDetail, newItem: LogEntryDetail): Boolean {
            return when (oldItem) {
                is LogEntryDetail.AddIngredient -> with(newItem as LogEntryDetail.AddIngredient) {
                    description == oldItem.description && ingredient == oldItem.ingredient
                }
                is LogEntryDetail.Text -> with(newItem as LogEntryDetail.Text) {
                    description == oldItem.description
                }
                is LogEntryDetail.Image -> with(newItem as LogEntryDetail.Image) {
                    url == oldItem.url
                }
            }
        }

    }
}

sealed class LogEntryDetail(@ItemType val itemType: Int) {
    data class Text(val description: String) : LogEntryDetail(TEXT)
    data class AddIngredient(val ingredient: Ingredient, val description: String) : LogEntryDetail(ADD_INGREDIENT)
    data class Image(val url: String) : LogEntryDetail(IMAGE)
}

sealed class LogEntryDetailViewHolder<T : LogEntryDetail>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)

    class TextVH(parent: ViewGroup) : LogEntryDetailViewHolder<LogEntryDetail.Text>(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_log_entry_details_text, parent, false)
    ) {
        private val binding = ListItemLogEntryDetailsTextBinding.bind(itemView)

        override fun bind(item: LogEntryDetail.Text) {
            binding.description.text = item.description
        }
    }

    class AddIngredientVH(parent: ViewGroup) : LogEntryDetailViewHolder<LogEntryDetail.AddIngredient>(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_log_entry_details_text, parent, false)
    ) {
        private val binding = ListItemLogEntryDetailsTextBinding.bind(itemView)

        override fun bind(item: LogEntryDetail.AddIngredient) {
            // TODO Define and handle other fancy stuff
            val description = item.description
            val startIndex = description.indexOf("{ingredient}")
            val ingredientName = item.ingredient.name
            val builder = SpannableStringBuilder(description.replace("{ingredient}", ingredientName))
            builder.setSpan(StyleSpan(Typeface.BOLD_ITALIC), startIndex, startIndex + ingredientName.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            binding.description.text = builder
        }
    }

    class ImageVH(parent: ViewGroup) : LogEntryDetailViewHolder<LogEntryDetail.Image>(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_log_entry_details_image, parent, false)
    ) {
        private val binding = ListItemLogEntryDetailsImageBinding.bind(itemView)
        override fun bind(item: LogEntryDetail.Image) {
            Glide.with(itemView)
                .load(item.url)
                .centerCrop()
                .into(binding.image)
        }
    }
}
