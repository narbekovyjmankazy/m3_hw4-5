package com.example.m3_hw4_5;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_hw4_5.databinding.ItemMessageBinding;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private final List<Message> messages;

    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Подключаем ViewBinding для элемента списка
        ItemMessageBinding binding = ItemMessageBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Привязываем данные
        holder.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMessageBinding binding;

        ViewHolder(@NonNull ItemMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Message message) {
            // Устанавливаем текст сообщения
            binding.messageTextView.setText(message.getText());

            // Устанавливаем фон в зависимости от отправителя
            binding.messageTextView.setBackgroundResource(
                    message.isUser() ? R.drawable.user_message_bg : R.drawable.bot_message_bg
            );

            // Проверяем родительский контейнер
            ViewGroup.LayoutParams params = binding.messageTextView.getLayoutParams();
            if (params instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) params;
                layoutParams.gravity = message.isUser() ? Gravity.END : Gravity.START;
                binding.messageTextView.setLayoutParams(layoutParams);
            }
        }
    }
}
