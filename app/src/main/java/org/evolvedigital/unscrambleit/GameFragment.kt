package org.evolvedigital.unscrambleit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.evolvedigital.unscrambleit.databinding.GameFragmentBinding


class GameFragment : Fragment() {

//    Binding instance to game_fragment xml layout
    private lateinit var binding: GameFragmentBinding

//    Create a ViewModel the first time the fragment is created
//    If the fragment is re-created, it receives the same GameViewModel instance created by the
//    first fragment
    private val viewModel: GameViewModel by viewModels()
}