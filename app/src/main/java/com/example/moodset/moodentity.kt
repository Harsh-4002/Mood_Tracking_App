package com.example.moodset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood-table")
data class moodentity(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val time:String,
    val date:String,
    val mood:String,

    val reason:String
    )
/*
<RadioButton
android:id="@+id/emoji"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="emoji"
android:textSize="15sp"
tools:ignore="MissingConstraints" />
<TextView
android:id="@+id/tvrvdate"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:textSize="15sp"
app:layout_constraintTop_toBottomOf="@id/tvrvTime"
android:text="1:02:2022"
tools:ignore="UnknownId" />

 */