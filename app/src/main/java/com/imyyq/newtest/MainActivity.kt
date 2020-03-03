package com.imyyq.newtest

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var vp: ViewPager2
    private val strings = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vp = findViewById(R.id.vp)

        for (i in 0..100) {
            strings.add("Item $i")
        }

        vp.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return strings.size
            }

            override fun createFragment(position: Int): Fragment {
                return TestFragment.create(strings[position], position)
            }
        }
    }

    fun onClick(v: View) {
        vp.currentItem = 49
    }

    class TestFragment : Fragment() {
        private var mPosition: Int = -1
        private var mText = ""

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            Log.e("TestFragment", "onCreateView,mPosition:$mPosition")

            val t = RecyclerView(context!!)
            t.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            t.layoutManager = LinearLayoutManager(context)

            val list = mutableListOf<Int>()
            list.add(mPosition)

            for (i in -1000..0) {
                list.add(i)
            }

            t.adapter = object : RecyclerView.Adapter<MyViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                    return MyViewHolder(TextView(context))
                }

                override fun getItemCount(): Int {
                    return list.size
                }

                override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                    holder.bind(list[position])
//                    holder.bind(position)
                }
            }
            return t

//            val t = TextView(context)
//            t.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//            t.setBackgroundColor(Color.BLUE)
//            t.text = "item $mText"
//            return t
        }

        inner class MyViewHolder(private val tx: TextView) : RecyclerView.ViewHolder(tx) {
            fun bind(position: Int) {
                tx.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                tx.text = "[mPosition] $position"
            }
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            mPosition = arguments?.getInt("mPosition", -1)!!
            mText = arguments?.getString("mText", "")!!

            Log.e("TestFragment", "onAttach,mPosition:$mPosition")
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.e("TestFragment", "onCreate,mPosition:$mPosition")
        }

        override fun setUserVisibleHint(isVisibleToUser: Boolean) {
            super.setUserVisibleHint(isVisibleToUser)
            Log.e("TestFragment", "setUserVisibleHint,mPosition:$mPosition")
        }

        override fun onStart() {
            super.onStart()
            Log.e("TestFragment", "onStart,mPosition:$mPosition")
        }

        override fun onResume() {
            super.onResume()
            Log.e("TestFragment", "onResume,mPosition:$mPosition")
        }


        override fun onPause() {
            super.onPause()
            Log.e("TestFragment", "onPause,mPosition:$mPosition")
        }

        override fun onStop() {
            super.onStop()
            Log.e("TestFragment", "onStop,mPosition:$mPosition")
        }

        override fun onDestroyView() {
            super.onDestroyView()
            Log.e("TestFragment", "onDestroyView,mPosition:$mPosition")
        }

        override fun onDestroy() {
            super.onDestroy()
            Log.e("TestFragment", "onDestroy,mPosition:$mPosition")
        }

        override fun onDetach() {
            super.onDetach()
            Log.e("TestFragment", "onDetach,mPosition:$mPosition")
        }

        companion object {
            fun create(text: String, position: Int): TestFragment {
                val fragment = TestFragment()
                val bundle = Bundle()
                bundle.putString("mText", text)
                bundle.putInt("mPosition", position)
                fragment.arguments = bundle
                return fragment
            }
        }
    }
}

