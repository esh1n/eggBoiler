/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.ui

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.databinding.FragmentEggTimerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EggTimerFragment : Fragment(R.layout.fragment_egg_timer) {

    private val TOPIC = "breakfast"

    //   app:elapsedTime="@{eggTimerViewModel.elapsedTime}"
    // android:onItemSelected="@{(parent, view, selection, id)-> eggTimerViewModel.setTimeSelected(selection)}"
    //        android:selectedItemPosition="@{eggTimerViewModel.timeSelection}"
    //   android:checked="@{eggTimerViewModel.isAlarmOn}"
    //        android:onCheckedChanged="@{(button, on)-> eggTimerViewModel.setAlarm(on)}"

    private var binding: FragmentEggTimerBinding? = null
    private val viewModel: EggTimerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEggTimerBinding.bind(view)
        // TODO: Step 1.7 call create channel
        observeVMChanges()
    }

    private fun observeVMChanges() {
        viewModel.elapsedTime.observe(viewLifecycleOwner) { elapsedTime ->
            binding?.textView?.setElapsedTime(elapsedTime)
        }
        viewModel.timeSelection.observe(viewLifecycleOwner) { selection ->
            binding?.minutesSpinner?.setOnItemClickListener(null)
            binding?.minutesSpinner?.setSelection(selection)
            binding?.minutesSpinner?.setOnItemClickListener { _, _, selection, _ ->
                viewModel.setTimeSelected(selection)
            }
        }
        viewModel.isAlarmOn.observe(viewLifecycleOwner) { isAlarmOn ->
            binding?.onOffSwitch?.setOnCheckedChangeListener(null)
            binding?.onOffSwitch?.isChecked = isAlarmOn
            binding?.onOffSwitch?.setOnCheckedChangeListener{ view,isChecked ->
                 viewModel.setAlarm(isChecked)
            }
        }
    }


    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 1.6 START create a channel

        // TODO: Step 1.6 END create a channel

    }

    fun TextView.setElapsedTime(value: Long) {
        val seconds = value / 1000
        text = if (seconds < 60) seconds.toString() else DateUtils.formatElapsedTime(seconds)
    }

    companion object {
        fun newInstance() = EggTimerFragment()
    }
}

