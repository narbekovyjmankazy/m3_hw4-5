package com.example.m3_hw4_5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.m3_hw4_5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements InputFragment.MessageSendListener {

    private ActivityMainBinding binding;
    private ChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatFragment = new ChatFragment();
        InputFragment inputFragment = new InputFragment();

        inputFragment.setMessageSendListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(binding.chatContainer.getId(), chatFragment)
                .replace(binding.inputContainer.getId(), inputFragment)
                .commit();
    }

    @Override
    public void onMessageSend(String message) {
        chatFragment.addMessage(message, true); // User message
        chatFragment.addMessage("Автоответчик: Принято!", false); // Bot response
    }
}
