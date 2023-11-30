package com.example.formapp;

import static com.example.formapp.Constant.KEY_USER_INTENT;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.formapp.databinding.ActivityUserDetailBinding;

public class UserDetailActivity extends AppCompatActivity {

    private ActivityUserDetailBinding binding;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = getIntent().getExtras().getParcelable(KEY_USER_INTENT);
        loadUserScreen();
        binding.backButton.setOnClickListener(view -> finish());
    }

    private void loadUserScreen() {
        loadPhoto();
        binding.rutText.setText(getString(R.string.label_rut, user.rut));
        binding.nameText.setText(getString(R.string.label_name, user.name));
        binding.ageText.setText(getString(R.string.label_age, Integer.toString(user.age)));
    }

    private void loadPhoto() {
        if (user.photo != null && !user.photo.isEmpty()) {

        }
    }
}
