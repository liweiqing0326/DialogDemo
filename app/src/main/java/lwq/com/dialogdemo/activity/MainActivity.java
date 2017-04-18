package lwq.com.dialogdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lwq.com.dialogdemo.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_nomal)
    Button btnNomal;
    @BindView(R.id.btn_Custom)
    Button btnCustom;
    @BindView(R.id.btn_DialogFragment)
    Button btnDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_nomal, R.id.btn_Custom, R.id.btn_DialogFragment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_nomal:
                Intent intent = new Intent(this, NormalDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_Custom:
                Intent intent1 = new Intent(this, CustomDialogActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_DialogFragment:
                Intent intent2 = new Intent(this, DialogFragmentActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
