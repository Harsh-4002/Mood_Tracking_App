package com.example.moodset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moodset.databinding.RecyclerviewitemBinding

class ItemAdapter(private  val items:ArrayList<moodentity>):RecyclerView.Adapter<ItemAdapter.ViewHolder>(){


    class  ViewHolder(binding:RecyclerviewitemBinding):RecyclerView.ViewHolder(binding.root)
    {


        val llMain=binding.cl
        val tim =binding.tvRvTime
        val date=binding.tvRvDate
        val mod=binding.tvRvMood
        val reason=binding.tvRvReason

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(RecyclerviewitemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
    ////    TODO("Not yet implemented")
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // TODO("Not yet implemented")
    //    val context=holder.itemView.context
        val item=items[position]
        holder.tim.text=item.time

holder.date.text=item.date
    holder.mod.text=item.mood
        holder.reason.text=item.reason
        if(position%2==0){

            holder.llMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.colorLightGrey))
        }
        else{
            holder.llMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
        }
    }


    }


