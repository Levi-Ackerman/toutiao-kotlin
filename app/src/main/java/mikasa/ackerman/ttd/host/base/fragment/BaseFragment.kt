package mikasa.ackerman.ttd.host.base.fragment

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
 * 2020/5/8 10:07 PM
 */

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


/**
 * 页面描述：fragment 基类
 *
 * Created by ditclear on 2017/9/27.
 */

abstract class BaseFragment<VB : ViewDataBinding> : Fragment(), Presenter {

    protected val mBinding by lazy { DataBindingUtil.inflate<VB>(layoutInflater, getLayoutId(), null, false) }

    protected lateinit var mContext: Context

    protected var visible = false

    /**
     * 标志位，标志已经初始化完成
     */
    protected var isPrepared: Boolean = false
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    protected var hasLoadOnce: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArgs(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = activity ?: throw Exception("activity 为null")
        retainInstance = true
        initView()
        // 加载数据
        loadData(true);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindVm()
        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    abstract fun bindVm()

    open fun initArgs(savedInstanceState: Bundle?) {

    }

    open fun initView(){}
    abstract override fun loadData(isRefresh: Boolean)

    abstract fun getLayoutId(): Int

    fun toast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

//    fun toastSuccess(msg: String?) {
//        msg?.let { activity?.toast(it, ToastType.SUCCESS) }
//    }
//
//    fun toastFailure(error: Throwable) {
//        activity?.dispatchFailure(error)
//    }

    override fun onClick(v: View?) {

    }

    protected fun <T> autoWired(key: String, default: T? = null): T? =
            arguments?.let { findWired(it, key, default) }

    private fun <T> findWired(bundle: Bundle, key: String, default: T? = null): T? {
        return if (bundle.get(key) != null) {
            try {
                bundle.get(key) as T
            } catch (e: ClassCastException) {
                e.printStackTrace()
                null
            }
        } else default

    }

}