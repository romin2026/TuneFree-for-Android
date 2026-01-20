package com.dirror.music.ui.dialog

import android.content.Context
import android.content.Intent
import com.dirror.music.App
import com.dirror.music.databinding.DialogPlayMoreBinding
import com.dirror.music.music.standard.data.StandardSongData
import com.dirror.music.ui.activity.PlayHistoryActivity
import com.dirror.music.ui.base.BaseBottomSheetDialog
import com.dirror.music.util.toast

class PlayerMenuMoreDialog(context: Context) : BaseBottomSheetDialog(context) {

    private val binding: DialogPlayMoreBinding = DialogPlayMoreBinding.inflate(layoutInflater)

    init {
        setContentView(binding.root)
    }


    private var song: StandardSongData? = null

    override fun initView() {

        App.musicController.value?.getPlayingSongData()?.value?.let { it ->
            binding.tvSongName.text = it.name
            song = it
        }
    }

    override fun initListener() {
        binding.apply {
            // 歌曲信息
            itemSongInfo.setOnClickListener {
                App.musicController.value?.getPlayingSongData()?.value?.let { it1 ->
                    SongInfoDialog(context, it1).show()
                }
                dismiss()
            }

            // 播放历史
            itemPlayHistory.setOnClickListener {
                it.context.startActivity(Intent(it.context, PlayHistoryActivity::class.java))
                dismiss()
            }

            timeClose.setOnClickListener {
                dismiss()
                TimingOffDialog(context).show()
            }
        }
    }

}
