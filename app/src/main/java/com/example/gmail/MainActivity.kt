package com.example.gmail

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmail.Adapters.ItemAdapter
import com.example.gmail.Models.GmailModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity(), ItemClickListener {
    lateinit var gmails: MutableList<GmailModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun getTime(before: Int): String {
            val calendar = Calendar.getInstance()
            // Trừ đi before phút
            calendar.add(Calendar.MINUTE, -before)
            // Định dạng thời gian theo kiểu "hh:mm a"
            val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
            return formatter.format(calendar.time)
        }

        gmails = mutableListOf()

        // Dữ liệu giả cho email
        val senders = listOf(
            "Alice", "Bob", "Charlie", "David", "Eva",
            "Frank", "Grace", "Hannah", "Ivy", "Jack",
            "Kathy", "Liam", "Mona", "Nina"
        )

        val contents = listOf(
            "Hi, how are you doing today?",
            "Don't forget about our meeting tomorrow.",
            "Just checking in on the project status.",
            "Can you send me the files we discussed?",
            "Happy birthday! Hope you have a great day!",
            "Let's catch up soon!",
            "Are you available for a quick call?",
            "I've attached the report for your review.",
            "What do you think about the proposal?",
            "I'm looking forward to our collaboration.",
            "Here's the update on the budget.",
            "Let me know if you need anything else.",
            "Thanks for your help with the project!",
            "Looking forward to hearing from you!",
            "Please confirm our appointment."
        )

        repeat(senders.size) {
            gmails.add(
                GmailModel(
                    senders[it],
                    contents[it],
                    getTime(it * 10),
                    resources.getIdentifier("avatar${it + 1}", "drawable", packageName)
                )
            )
        }

        val adapter = ItemAdapter(gmails, this)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun OnItemClicked(position: Int) {
        Log.v("TAG", "${gmails[position]}")
    }
}
