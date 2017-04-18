package lwq.com.dialogdemo.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lwq.com.dialogdemo.R;


/**
 * Created by Administrator on 2017/3/4.
 */

public class BottomDialogFragment extends DialogFragment {

    @BindView(R.id.regards_tv_100_coins)
    TextView regardsTv100Coins;
    @BindView(R.id.regards_iv_100_coins)
    ImageView regardsIv100Coins;
    @BindView(R.id.regards_ll_first_container)
    LinearLayout regardsLlFirstContainer;
    @BindView(R.id.regards_tv_2_yuan)
    TextView regardsTv2Yuan;
    @BindView(R.id.regards_iv_2_yuan)
    ImageView regardsIv2Yuan;
    @BindView(R.id.regards_ll_second_container)
    LinearLayout regardsLlSecondContainer;
    @BindView(R.id.regards_tv_8_yuan)
    TextView regardsTv8Yuan;
    @BindView(R.id.regards_iv_8_yuan)
    ImageView regardsIv8Yuan;
    @BindView(R.id.regards_ll_third_container)
    LinearLayout regardsLlThirdContainer;
    @BindView(R.id.regards_tv_12_yuan)
    TextView regardsTv12Yuan;
    @BindView(R.id.regards_iv_12_yuan)
    ImageView regardsIv12Yuan;
    @BindView(R.id.regards_ll_forth_container)
    LinearLayout regardsLlForthContainer;
    @BindView(R.id.regards_tv_coin_count)
    TextView regardsTvCoinCount;
    @BindView(R.id.regards_tv_send)
    TextView regardsTvSend;
    /**
     * 框架
     */
    private ArrayList<LinearLayout> mLayouts;
    /**
     * 文字类型
     */
    private ArrayList<TextView> mTvTypes;
    /**
     * 图像类型
     */
    private ArrayList<ImageView> mIvTypes;
    /**
     * 当前类型
     */
    private int mType = 0;
    /**
     * 当前金币数
     */
    private int mCoinCount = 2310;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        //设置Content前设定
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置布局
        dialog.setContentView(R.layout.fragment_bottom);
        //外部点击取消
        dialog.setCanceledOnTouchOutside(true);
        //设置宽度为屏宽，靠近屏幕底部
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //紧贴底部
        layoutParams.gravity = Gravity.BOTTOM;
        //宽度持平
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);

        ButterKnife.bind(this, dialog);
        // 初始化控件组
        initViewArray();
        // 初始化布局
        initLayout();
        //设置监听
        setListener();
        return dialog;
    }

    private void setListener() {
        regardsTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typeStr = "金币不足";
                switch (mType) {
                    case 0:
                        if (countCoins(100))
                            typeStr = "一字之师";
                        break;
                    case 1:
                        if (countCoins(200))
                            typeStr = "妙语连珠";
                        break;
                    case 2:
                        if (countCoins(800))
                            typeStr = "学识丰富";
                        break;
                    case 3:
                        if (countCoins(1200))
                            typeStr = "博学多才";
                        break;
                    default:
                        break;
                }
                Toast.makeText(getContext(), typeStr, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 消费金币
     *
     * @param count 金币数
     * @return 是否消费
     */
    private boolean countCoins(int count) {
        int end = mCoinCount - count;
        if (end > 0) {
            mCoinCount = end;
            regardsTvCoinCount.setText(String.valueOf("您共有金币" + end));
            return true;
        } else {
            return false;
        }
    }

    /**
     * 初始化布局
     */
    private void initLayout() {
        chooseRegardsType(mType); // 选择默认类型
        for (int i = 0; i < mLayouts.size(); i++) {
            final int tmp = i;
            LinearLayout ll =  mLayouts.get(i);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mType = tmp;
                    chooseRegardsType(mType);
                }
            });
        }
    }

    /**
     * 选择类型
     *
     * @param type 类型
     */
    private void chooseRegardsType(int type) {
        int size = mTvTypes.size();
        for (int i = 0; i < size; ++i) {
            if (i != type) {
                mTvTypes.get(i).setEnabled(true);
                mIvTypes.get(i).setEnabled(true);
            } else {
                mTvTypes.get(i).setEnabled(false);
                mIvTypes.get(i).setEnabled(false);
            }
        }
    }

    /**
     * 初始化类型数组, 文字和图片
     */
    private void initViewArray() {
        mLayouts = new ArrayList<>();
        mTvTypes = new ArrayList<>();
        mIvTypes = new ArrayList<>();

        mLayouts.add(regardsLlFirstContainer);
        mLayouts.add(regardsLlSecondContainer);
        mLayouts.add(regardsLlThirdContainer);
        mLayouts.add(regardsLlForthContainer);

        mTvTypes.add(regardsTv100Coins);
        mTvTypes.add(regardsTv2Yuan);
        mTvTypes.add(regardsTv8Yuan);
        mTvTypes.add(regardsTv12Yuan);

        mIvTypes.add(regardsIv100Coins);
        mIvTypes.add(regardsIv2Yuan);
        mIvTypes.add(regardsIv8Yuan);
        mIvTypes.add(regardsIv12Yuan);
    }

}
