package com.example.m3_hw4_5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.m3_hw4_5.databinding.FragmentInputBinding;

public class InputFragment extends Fragment {

    private FragmentInputBinding binding;
    private MessageSendListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInputBinding.inflate(inflater, container, false);

        binding.sendButton.setOnClickListener(v -> {
            String message = binding.editText.getText().toString().trim();
            if (!message.isEmpty() && listener != null) {
                listener.onMessageSend(message);
                binding.editText.setText("");
            }
        });

        return binding.getRoot();
    }

    public void setMessageSendListener(MessageSendListener listener) {
        this.listener = listener;
    }

    public interface MessageSendListener {
        void onMessageSend(String message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
