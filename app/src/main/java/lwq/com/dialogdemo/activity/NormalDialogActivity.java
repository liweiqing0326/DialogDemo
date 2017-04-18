package lwq.com.dialogdemo.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lwq.com.dialogdemo.R;

public class NormalDialogActivity extends AppCompatActivity {

    @BindView(R.id.btn_NotifyDialog)
    Button btnNotifyDialog;
    @BindView(R.id.btn_ListDialog)
    Button btnListDialog;
    @BindView(R.id.btn_SinpleChioceDialog)
    Button btnSinpleChioceDialog;
    @BindView(R.id.btn_MultiChioceDialog)
    Button btnMultiChioceDialog;
    @BindView(R.id.btn_ProgressDialog)
    Button btnProgressDialog;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;
    private AlertDialog.Builder builder;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_dialog);
        ButterKnife.bind(this);
        context = this;
    }

    @OnClick({R.id.btn_NotifyDialog, R.id.btn_ListDialog, R.id.btn_SinpleChioceDialog, R.id.btn_MultiChioceDialog, R.id.btn_ProgressDialog, R.id.btn1, R.id.btn3, R.id.btn5, R.id.btn8, R.id.btn9})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("温馨提示");//标题
                builder.setMessage("天气冷，注意保暖");//设置对话框的正文文字
                builder.setIcon(R.mipmap.ic_launcher);//设置对话框的标题的图标
                builder.create();//获取AlertDialog的对象
                builder.show();//显示出对话框
                break;
            case R.id.btn3:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("多个按钮对话框")//标题
                        .setMessage("请选择其中一个")//设置对话框的正文文字
                        .setIcon(R.mipmap.ic_launcher)//设置对话框的标题的图标
                        .setPositiveButton("我没玩够", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "继续浏览精彩内容", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("开启", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "起床了", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("我累了，要休息一下", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "欢迎再来", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();//获取AlertDialog的对象
                builder.show();//显示出对话框
                break;
            case R.id.btn5:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("带Adapter的对话框");//标题
                builder.setIcon(R.mipmap.ic_launcher);//设置对话框的标题的图标
                final List<Map<String, Object>> list = new ArrayList<>();
                int arrImg[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                        R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                        R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
                for (int i = 0; i < arrImg.length; i++) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("img", arrImg[i]);
                    map.put("title", "爱开发" + i);
                    list.add(map);
                }
                SimpleAdapter adapter = new SimpleAdapter(context, list, R.layout.list_item, new String[]{"img", "title"}, new int[]{R.id.iv, R.id.tv});
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "你选择了" + list.get(which).get("title").toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create()//获取AlertDialog的对象
                        .show();//显示出对话框
                break;
            case R.id.btn8:
                //日期对话框
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(context, year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
                    }
                }, 2017, 02, 9);
                datePickerDialog.show();
                break;
            case R.id.btn9:
                //时间对话框
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(context, hourOfDay + "小时" + minute + "分钟", Toast.LENGTH_SHORT).show();
                    }
                }, 17, 49, true);
                timePickerDialog.show();
                break;
            case R.id.btn_NotifyDialog:
                //初始化
                builder = new AlertDialog.Builder(this);
                builder.setTitle("通知对话框：")//设置标题
                        .setMessage("通知对话框弹出了")//设置内容
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {//设置dialog确认按钮的点击事件
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(NormalDialogActivity.this, "OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {//设置dialog取消按钮的点击事件
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(NormalDialogActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)//设置是否可以通过返回键消失, 默认true
                        .create();//创建AlterDialog
                //显示AlterDialog
                builder.show();
                break;
            case R.id.btn_ListDialog:
                builder = new AlertDialog.Builder(this);
                final String[] items = new String[]{"8", "6", "2", "1", "3"};//设置列表的内容
                builder.setTitle("请选中您喜欢的数字:")//设置标题
                        .setIcon(R.mipmap.ic_launcher)//设置图标
                        .setItems(items, new DialogInterface.OnClickListener() {//设置列表的点击事件
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(NormalDialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)//设置是否可以通过返回键消失, 默认true
                        .create();//创建AlterDialog
                //显示AlterDialog
                builder.show();
                break;
            case R.id.btn_SinpleChioceDialog:
                final String[] items1 = new String[]{"男", "女", "妖"};
                builder = new AlertDialog.Builder(this);
                builder.setTitle("选择性别:")//设置标题
                        .setIcon(R.mipmap.ic_launcher)//设置图标
                        .setSingleChoiceItems(items1, 2, new DialogInterface.OnClickListener() {//设置列表的单选事件

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(NormalDialogActivity.this, items1[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {//设置dialog确认按钮的点击事件

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(NormalDialogActivity.this, "OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {//设置dialog取消按钮的点击事件

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(NormalDialogActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)//设置是否可以通过返回键消失, 默认true
                        .create();//创建AlterDialog
                //显示AlterDialog
                builder.show();
                break;
            case R.id.btn_MultiChioceDialog:
                final String[] items2 = new String[]{"抽烟", "喝酒", "烫头"};//设置多选的内容
                final boolean[] checkedItems = new boolean[]{false, true, false};//设置多选默认状态
                builder = new AlertDialog.Builder(this);
                builder.setTitle("于谦:")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMultiChoiceItems(items2, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {//设置多选的点击事件

                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkedItems[which] = isChecked;
                                Toast.makeText(NormalDialogActivity.this, items2[which] + " 状态: " + isChecked, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {//设置dialog确认按钮的点击事件

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(NormalDialogActivity.this, "OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {//设置dialog取消按钮的点击事件

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(NormalDialogActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setCancelable(false)//设置是否可以通过返回键消失, 默认true
                        .create();//创建AlterDialog
                //显示AlterDialog
                builder.show();
                break;
            case R.id.btn_ProgressDialog:
                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setTitle("提醒");//设置进度对话框的标题
                dialog.setMessage("正在加载中,请稍后...");//设置进度对话框的内容
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置进度对话框的样式
                dialog.setMax(100);//设置进度对话框的最大进度
                dialog.setCancelable(false);
                dialog.show();//在show时, 别忘了初始化进度
                dialog.setProgress(23);//设置进度
                new Thread() {
                    public void run() {
                        while (true) {
                            if (dialog.getProgress() == 100) {
                                return;
                            }
                            SystemClock.sleep(100);
                            dialog.incrementProgressBy(1);//更新进度,每一次以5来递增(dialog.setProgress(dialog.getProgress() + 5))
                            if (dialog.getProgress() >= dialog.getMax()) {
                                runOnUiThread(new Runnable() {//在主线程执行
                                    @Override
                                    public void run() {
                                        Toast.makeText(NormalDialogActivity.this, "加载完毕", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });
                            }
                        }
                    }
                }.start();
                break;
        }
    }
}
