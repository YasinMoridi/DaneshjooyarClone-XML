package mr.moridi.videoplayer.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import mr.moridi.videoplayer.databinding.ActivityMainBinding

class ViewMainActivity : FrameLayout {

    constructor(contextInstance: Context) : super(contextInstance)


    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

}