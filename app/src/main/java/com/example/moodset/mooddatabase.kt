package com.example.moodset

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [moodentity::class], version = 5)
abstract class mooddatabase:RoomDatabase() {
    abstract  fun mooddao():MOODDao


    companion object{


        @Volatile
        private  var INSTANCE:mooddatabase?=null
        fun getInstance(context:Context):mooddatabase{
            synchronized(this){

                var instance= INSTANCE
                if(instance ==null){


                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        mooddatabase::class.java,
                        "mood database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
          return  instance
            }

        }

    }
}