package mikasa.ackerman.ttd.host.base.ui

import android.widget.RadioButton

/**
 * TTD
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 * <p>
 *
 * <br>
 * 用法:
 * <pre>
 * </pre>
 * </p>
 *
 * <p>Copyright: Copyright (c) 2020</p>
 *
 * @author zhengxian.lzx@alibaba-inc.com
 * @version 1.0
 * 2020/5/10 11:30 PM
 */
abstract class RBTab : Tab<RadioButton>{
    constructor(name: String?, tabId: Int) : super(name, tabId)
}