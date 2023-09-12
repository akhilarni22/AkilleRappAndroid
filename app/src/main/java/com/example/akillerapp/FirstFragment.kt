package com.example.akillerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.akillerapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.trackcallfirstsegment.setOnClickListener {
            /*rudderClient.track(
                "Track event",
                RudderProperty().putValue("track first fragment", "product_001")
            )*/

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)


            //Toast.makeText(this, "Track from first segment clicked", Toast.LENGTH_SHORT).show()
         }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}