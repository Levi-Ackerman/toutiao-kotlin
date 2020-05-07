package mikasa.ackerman.ttd.host.demo.viewmodel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Retrofit;

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
 * 2020/4/27 9:37 PM
 */
public class SubTabViewModel extends ViewModel {
    private MutableLiveData<List<SubTab>> mSubtabs = new MutableLiveData<>();

    public LiveData<List<SubTab>> getSubtabs() {
        return mSubtabs;
    }

    public void requestSubTabs() {

    }
}
