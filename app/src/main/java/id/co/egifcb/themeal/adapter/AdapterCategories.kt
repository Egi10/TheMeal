package id.co.egifcb.themeal.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.co.egifcb.themeal.R
import id.co.egifcb.themeal.model.Categories
import kotlinx.android.synthetic.main.layout_list_categories.view.*

class AdapterCategories(private val context: Context, private val list: List<Categories>, private val listener: (Categories) -> Unit)
    : RecyclerView.Adapter<AdapterCategories.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            AdapterCategories.ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_categories, p0, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(list[p1], listener, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(categories: Categories, listener: (Categories) -> Unit, context: Context) {
            Glide.with(context).load(categories.strCategoryThumb).into(itemView.iv_image)
            itemView.tv_category.text = categories.strCategory
            itemView.setOnClickListener {
                listener(categories)
            }
        }
    }
}