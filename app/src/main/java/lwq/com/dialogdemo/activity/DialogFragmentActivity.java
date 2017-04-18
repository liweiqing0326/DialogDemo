package lwq.com.dialogdemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lwq.com.dialogdemo.Fragment.BottomDialogFragment;
import lwq.com.dialogdemo.Fragment.EditNameDialogFragment;
import lwq.com.dialogdemo.Fragment.FireMissilesDialogFragment;
import lwq.com.dialogdemo.Fragment.LoginDialogFragment;
import lwq.com.dialogdemo.R;
import lwq.com.dialogdemo.Dialog.StepDialog;
import lwq.com.dialogdemo.Fragment.ZoomOutPageTransformer;

public class DialogFragmentActivity extends AppCompatActivity implements LoginDialogFragment.LoginInputListener {

    @BindView(R.id.btn_BottomDialog)
    Button btnBottomDialog;
    @BindView(R.id.btn_ConfimDialog)
    Button btnConfimDialog;
    @BindView(R.id.btn_EditDialog)
    Button btnEditDialog;
    @BindView(R.id.btn_LoginDialog)
    Button btnLoginDialog;
    @BindView(R.id.btn_StepDialog)
    Button btnStepDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_BottomDialog, R.id.btn_ConfimDialog, R.id.btn_EditDialog, R.id.btn_LoginDialog,R.id.btn_StepDialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_BottomDialog:
                FragmentManager fm = getSupportFragmentManager();
                BottomDialogFragment editNameDialog = new BottomDialogFragment();
                editNameDialog.show(fm, "fragment_bottom_dialog");
                break;
            case R.id.btn_ConfimDialog:
                FireMissilesDialogFragment test1 = new FireMissilesDialogFragment();
                test1.show(getSupportFragmentManager(), "");
                break;
            case R.id.btn_EditDialog:
                EditNameDialogFragment editNameDialog1 = new EditNameDialogFragment();
                editNameDialog1.show(getFragmentManager(), "EditNameDialog");
                break;
            case R.id.btn_LoginDialog:
                LoginDialogFragment dialog = new LoginDialogFragment();
                dialog.show(getFragmentManager(), "loginDialog");
                break;
            case R.id.btn_StepDialog:
                StepDialog.getInstance()
                        .setImages(new int[]{R.drawable.new_user_guide_1, R.drawable.new_user_guide_2, R.drawable.new_user_guide_3, R.drawable.new_user_guide_4})
                        .setCanceledOnTouchOutside(false)
                        .setPageTransformer(new ZoomOutPageTransformer())
                        .show(getFragmentManager());
                break;
        }
    }


    @Override
    public void onLoginInputComplete(String username, String password) {
        Toast.makeText(this, "帐号：" + username + ",  密码 :" + password, Toast.LENGTH_SHORT).show();
    }


}
