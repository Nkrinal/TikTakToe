package com.example.tiktaktoe

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.TypedValue
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var txtarr=ArrayList<TextView>()
    var v=0
    var paly=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setvalue(0,R.id.btn1)
        setvalue(1,R.id.btn2)
        setvalue(2,R.id.btn3)
        setvalue(3,R.id.btn4)
        setvalue(4,R.id.btn5)
        setvalue(5,R.id.btn6)
        setvalue(6,R.id.btn7)
        setvalue(7,R.id.btn8)
        setvalue(8,R.id.btn9)

        findViewById<ImageView>(R.id.replay).setOnClickListener {
            refresh()
        }

    }
    private fun setvalue(i: Int, btn1: Int) {
        txtarr.add(i,findViewById(btn1))
        txtarr[i].setOnClickListener {
            if(txtarr[i].text.toString().isEmpty() && paly)
            {
                if(v==1)
                {
                    txtarr[i].text="✖"
                    findViewById<TextView>(R.id.turn).setText("𝗢")
                    txtarr[i].setTextColor(Color.parseColor("#2EC5C0"))
                    v=0
                }
                else
                {
                    txtarr[i].text="𝗢"
                    findViewById<TextView>(R.id.turn).setText("✖")
                    txtarr[i].setTextColor(Color.parseColor("#F2B037"))
                    v=1
                }
                var music=MediaPlayer.create(this@MainActivity,R.raw.musicc)
                music.start()
                conditions(0,1,2)
                conditions(3,4,5)
                conditions(6,7,8)
                conditions(0,4,8)
                conditions(2,4,6)
                conditions(0,3,6)
                conditions(1,4,7)
                conditions(2,5,8)
                ties()
            }

        }
    }

    private fun ties() {
        for(i in txtarr.indices)
        {
            if(txtarr[i].text.isEmpty())
            {
                break
            }
            if(i==(txtarr.size-1) && paly)
            {
                var txt=findViewById<TextView>(R.id.txtt).text.toString().toInt()
                findViewById<TextView>(R.id.txtt).setText("${txt+1}")
                refresh()
            }
        }
    }

    private fun conditions(i: Int, i1: Int, i2: Int) {
        if(txtarr[i].text.toString().equals(txtarr[i1].text.toString()) && txtarr[i].text.toString().equals(txtarr[i2].text.toString()) && !txtarr[i].text.toString().equals("") && paly)
        {
            paly=false

            if(txtarr[i].text.equals("✖"))
            {
                var txtx=findViewById<TextView>(R.id.txtx).text.toString().toInt()
                findViewById<TextView>(R.id.txtx).setText("${txtx+1}")
                setbg(i,i1,i2, R.color.bgx,R.color.bg,"#1F3540","#2EC5C0")
            }
            else
            {
                var txtx=findViewById<TextView>(R.id.txto).text.toString().toInt()
                findViewById<TextView>(R.id.txto).setText("${txtx+1}")
                setbg(i,i1,i2, R.color.bgo,R.color.bg,"#1F3540","#F2B037")
            }
        }

    }

    fun setbg(i: Int, i1: Int, i2: Int, bgx: Int, bg: Int, s: String, s1: String)
    {
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadeout)
        CoroutineScope(Dispatchers.Main).launch {

            txtarr[i].setTextColor(Color.parseColor(s))
            txtarr[i].setBackgroundResource(bgx)
            delay(300)
            txtarr[i1].setTextColor(Color.parseColor(s))
            txtarr[i1].setBackgroundResource(bgx)
            delay(300)
            txtarr[i2].setTextColor(Color.parseColor(s))
            txtarr[i2].setBackgroundResource(bgx)

            txtarr[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
            txtarr[i1].setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
            txtarr[i2].setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)

            txtarr[i].startAnimation(animationFadeIn)
            txtarr[i1].startAnimation(animationFadeIn)
            txtarr[i2].startAnimation(animationFadeIn)
            delay(3000)

            txtarr[i].setTextColor(Color.parseColor(s1))
            txtarr[i].setBackgroundResource(bg)
            delay(100)
            txtarr[i1].setTextColor(Color.parseColor(s1))
            txtarr[i1].setBackgroundResource(bg)
            delay(100)
            txtarr[i2].setTextColor(Color.parseColor(s1))
            txtarr[i2].setBackgroundResource(bg)
            delay(100)

            refresh()
            txtarr[i].clearAnimation()
            txtarr[i1].clearAnimation()
            txtarr[i2].clearAnimation()

            paly=true

        }
    }


    private fun refresh() {
        var musicc=MediaPlayer.create(this@MainActivity,R.raw.cleardatamusic)
        CoroutineScope(Dispatchers.Main).launch {
                for(i in 0 until txtarr.size)
                {
                    musicc.start()
                    txtarr[i].setBackgroundResource(R.color.bg)
                    txtarr[i].text=""
                    delay(100)
                }
        }
        v=1
    }
}