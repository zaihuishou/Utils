package cruise.library.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by zhiqiang on 8/27/15.
 */
public class KeyBoardUtils extends BaseUtil {


    private KeyBoardUtils() {
        super();
    }

    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = getInputMethodManager(mContext);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    protected static InputMethodManager getInputMethodManager(Context mContext) {
        return (InputMethodManager) mContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = getInputMethodManager(mContext);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
