package lwq.com.dialogdemo.Fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import lwq.com.dialogdemo.R;

/**
 * Created by Administrator on 2017/3/4.
 */

public class LoginDialogFragment extends DialogFragment {
    private EditText mUsername;
    private EditText mPassword;

    public interface LoginInputListener {
        void onLoginInputComplete(String username, String password);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_login_dialog, null);
        mUsername = (EditText) view.findViewById(R.id.id_txt_username);
        mPassword = (EditText) view.findViewById(R.id.id_txt_password);
        builder.setView(view)
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        LoginInputListener listener = (LoginInputListener) getActivity();
                        listener.onLoginInputComplete(mUsername.getText().toString(), mPassword.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", null);
        return builder.create();
    }
}
