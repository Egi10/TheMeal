package id.co.egifcb.themeal.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.co.egifcb.themeal.R
import id.co.egifcb.themeal.model.Meals
import kotlinx.android.synthetic.main.layout_list_meals.view.*

class AdapterMeals(private val context: Context, private val list: List<Meals>, private val listener: (Meals) -> Unit)
    : RecyclerView.Adapter<AdapterMeals.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            AdapterMeals.ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_meals, p0, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(list[p1], listener, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(meals: Meals, listener: (Meals) -> Unit, context: Context) {
            Glide.with(context).load(meals.strMealThumb).into(itemView.iv_thumb)
            itemView.tv_meals.text = meals.strMeal
            itemView.setOnClickListener {
                listener(meals)
            }
        }
    }
}