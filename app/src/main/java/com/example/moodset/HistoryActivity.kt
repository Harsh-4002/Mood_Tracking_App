package com.example.moodset



import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager


import com.example.moodset.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch
//import java.util.zip.Inflater


class HistoryActivity : AppCompatActivity() {
    private var binding: ActivityHistoryBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.historytb)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.historytb?.setNavigationOnClickListener {
            onBackPressed()

        }

        val moodDao1=(application as Appmod).db.mooddao()
       if (intent.getBooleanExtra("history_clicked", false)) {

            lifecycleScope.launch {
moodDao1.fetchAllMoods().collect{
    val list=ArrayList(it)
    setup(list,moodDao1)
}
           }
        }


    }

    private fun setup(moodlist:ArrayList<moodentity>, moodDao1: MOODDao){

        if(moodlist.isNotEmpty()){

            val itemadap=ItemAdapter(moodlist)
            binding?.rv?.layoutManager=LinearLayoutManager(this)
            binding?.rv?.adapter=itemadap
        }
        else{
            binding?.rv?.visibility= View.GONE
        }



    }


}