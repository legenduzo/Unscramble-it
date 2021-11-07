package org.evolvedigital.unscrambleit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.evolvedigital.unscrambleit.databinding.GameFragmentBinding

// Fragment where the game is played. Contains the game logic
class GameFragment : Fragment() {

    private var score = 0
    private var currentWordCount = 0
    private var currentScambledWord = "test"

//    Binding instance to game_fragment xml layout
    private lateinit var binding: GameFragmentBinding

//    Create a ViewModel the first time the fragment is created
//    If the fragment is re-created, it receives the same GameViewModel instance created by the
//    first fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        inflate the layout xml and return the binding object instance
        binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        set up a click listener for submit and skip buttons
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }

//        Update the UI
        updateNextWordOnScreen()
        binding.score.text = getString(R.string.score, 0)
        binding.wordCount.text = getString(
            R.string.word_count, 0, MAX_NO_OF_WORDS)

    }

    /*
    * Checks the user's word, and updates the score accordingly.
    * Displays the next scrambled word.
    */
    private fun onSubmitWord() {
        currentScambledWord = getNextScrambledWord()
        currentWordCount++
        score += SCORE_INCREASE
        binding.wordCount.text = getString(R.string.word_count, currentWordCount, MAX_NO_OF_WORDS)
        binding.score.text = getString(R.string.score, score)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    /*
     * Skips the current word without changing the score.
     * Increases the word count.
     */
    private fun onSkipWord() {
        currentScambledWord = getNextScrambledWord()
        currentWordCount++
        binding.wordCount.text = getString(R.string.word_count, currentWordCount, MAX_NO_OF_WORDS)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    /*
     * Gets a random word for the list of words and shuffles the letters in it.
     */
    private fun getNextScrambledWord(): String {
        val tempWord = allWordsList.random().toCharArray()
        tempWord.shuffle()
        return String(tempWord)
    }

    /*
     * Re-initializes the data in the ViewModel and updates the views with the new data, to
     * restart the game.
     */
    private fun restartGame() {
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    /*
     * Exits the game.
     */
    private fun exitGame() {
        activity?.finish()
    }

    /*
    * Sets and resets the text field error status.
    */
    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }

    }

    private fun updateNextWordOnScreen() {
        binding.textViewUnscrambledWord.text = currentScambledWord
    }
}
