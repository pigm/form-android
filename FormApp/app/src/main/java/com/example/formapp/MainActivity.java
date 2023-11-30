package com.example.formapp;

import static com.example.formapp.Constant.KEY_USER_INTENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.formapp.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.skydoves.balloon.ArrowOrientation;
import com.skydoves.balloon.Balloon;
import com.skydoves.balloon.BalloonAnimation;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.titleText.setText(getString(R.string.title_form));
        binding.subTitleText.setText(getString(R.string.sub_title_form));
        binding.userImage.setOnClickListener(view -> manageTakePhoto());
        binding.visualizationButton.setOnClickListener(view -> goToVisualization());
    }

    @Override
    protected void onPause() {
        super.onPause();
        cleanEditText();
        clearFocus();
    }

    private void cleanEditText() {
        binding.rutEditText.setText("");
        binding.nameEditText.setText("");
        binding.ageEditText.setText("");
    }

    private void clearFocus() {
        binding.rutEditText.clearFocus();
        binding.nameEditText.clearFocus();
        binding.ageEditText.clearFocus();
    }

    private void manageTakePhoto() {
        showTooltip(binding.userImage, R.string.user_image_tooltip, 0.6f, 0.5f, Gravity.CENTER);
        showSnackBar(getString(R.string.warning_take_photo), R.drawable.ic_error_snackbar);
    }

    private User userCreate() {
        User user = new User();
        user.photo = "";
        user.rut = binding.rutEditText.getText().toString();
        user.name = binding.nameEditText.getText().toString();
        if (!binding.ageEditText.getText().toString().isEmpty()) {
            user.age = Integer.parseInt(binding.ageEditText.getText().toString());
        }
        return user;
    }

    private void goToVisualization() {
        User user = userCreate();
        if (!user.rut.isEmpty() && !user.name.isEmpty() && user.age != 0) {
            Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
            intent.putExtra(KEY_USER_INTENT, user);
            startActivity(intent);
        } else {
            showSnackBar(getString(R.string.error_visualization_user), R.drawable.ic_error_snackbar);
        }
    }

    private void showSnackBar(String message, int drawableId) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG);
        snackbar.setAnchorView(binding.visualizationButton);
        View snackBarLayout = snackbar.getView();
        TextView textView = snackBarLayout.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableId, 0, 0, 0);
        textView.setCompoundDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.snack_bar_icon_padding));
        snackbar.show();
    }

    private void showTooltip(View anchorView, int text, float widthRatio, float arrowPosition, int gravity) {
        Balloon tooltip = new Balloon.Builder(this).setArrowSize(10).setArrowOrientation(ArrowOrientation.TOP).setWidthRatio(widthRatio).setTextSize(16f).setPadding(8).setArrowPosition(arrowPosition).setCornerRadius(4f).setAlpha(1.0f).setTextGravity(gravity).setAutoDismissDuration(3500).setText(getString(text)).setTextColor(getResources().getColor(R.color.colorOnPrimary)).setBackgroundColor(getResources().getColor(R.color.colorPrimaryText)).setBalloonAnimation(BalloonAnimation.FADE).setLifecycleOwner(this).build();
        tooltip.showAlignBottom(anchorView);
        tooltip.setOnBalloonClickListener(view -> {
            tooltip.dismiss();
        });
        tooltip.setOnBalloonOutsideTouchListener((view, motionEvent) -> {
            tooltip.dismiss();
        });
    }
}