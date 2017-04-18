package lwq.com.dialogdemo.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lwq.com.dialogdemo.Dialog.DialogOnClickListener;
import lwq.com.dialogdemo.Dialog.PassWordDialog;
import lwq.com.dialogdemo.R;

public class CustomDialogActivity extends AppCompatActivity {

    @BindView(R.id.btn_LoadingDialog)
    Button btnLoadingDialog;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    private Context context;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        ButterKnife.bind(this);
        context = this;
    }

    @OnClick({R.id.btn_LoadingDialog, R.id.btn1,R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_LoadingDialog:
                builder = new AlertDialog.Builder(this);
                View root;
                root = View.inflate(this, R.layout.dialogui_loading_vertical, null);
                ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
                TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
                tvMsg.setText("加载中。。。");
                tvMsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "加载中", Toast.LENGTH_LONG).show();
                    }
                });
                pbBg.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.dialogui_rotate_mum));
                tvMsg.setTextColor(this.getResources().getColor(R.color.text_black));
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setContentView(root);
                break;
            case R.id.btn1:
                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_login, null);
                builder = new AlertDialog.Builder(this);
                builder.setView(view1);
                builder.create();
                final EditText et_phone = (EditText) view1.findViewById(R.id.et_phone);
                final EditText et_password = (EditText) view1.findViewById(R.id.et_password);
                Button btn_submit = (Button) view1.findViewById(R.id.btn_submit);
                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "手机号码:" + et_phone.getText().toString() + " 短信验证码:" + et_password.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            case R.id.btn2:
                PassWordDialog.showSecurityCodeInputDialog(this, new DialogOnClickListener() {
                    @Override
                    public void onClick(String str) {
                        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                        PassWordDialog.dialogDismiss();
                    }
                });
                break;
        }
    }
}
