//package com.app.repairo.app.activity.fcm
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
//import com.ethanhua.skeleton.Skeleton
//import com.app.repairo.app.R
//import com.app.repairo.app.activity.logreg.LoginActivity
//import com.app.repairo.app.adapter.NotificationAdapter
//import com.app.repairo.app.apis.ApiClient
//import com.app.repairo.app.apis.ApiInterface
//import com.app.repairo.app.model.notification.NotificationItem
//import com.app.repairo.app.model.notification.NotificationItemList
//import com.app.repairo.app.utils.ConstantValues
//import com.app.repairo.app.utils.Preferences
//import io.reactivex.Observer
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.Disposable
//import io.reactivex.schedulers.Schedulers
//import kotlinx.android.synthetic.main.activity_notification.*
//
//class NotificationActivity : BaseActivity() , NotificationAdapter.EventListener{
//    private var notificationItem: ArrayList<NotificationItem> = ArrayList()
//    private var adapter: NotificationAdapter? = null
//    private var skelton2: RecyclerViewSkeletonScreen? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        layoutInflater.inflate(R.layout.activity_notification, frameLayout)
////        btn_back_menu.setOnClickListener {
////            intent = Intent(this@NotificationActivity, HomeActivity::class.java)
////            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////            startActivity(intent)
////            finish()
////        }
//        rvNotification.setLayoutManager(LinearLayoutManager(this))
//        adapter = NotificationAdapter(this@NotificationActivity, notificationItem, this)
//        rvNotification.setAdapter(adapter)
//        if (Preferences.getBoolean(this@NotificationActivity, ConstantValues.KEY_IS_LOGIN)){
//            getNotificationApisCall()
//        }
//        else{
//            nodata.visibility = View.VISIBLE
//        }
//
//        Preferences.saveInt(this, ConstantValues.KEY_NOTIFICATION_COUNT, 0)
//    }
//
//
//
//    fun isNullOrEmpty(str: String?): Boolean {
//        return if (str != null && !str.isEmpty()) false else true
//    }
//
//    private fun getNotificationApisCall() {
//
//        if (!Preferences.getBoolean(this@NotificationActivity,ConstantValues.KEY_IS_LOGIN)){
//            val intent = Intent(applicationContext, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//            finishAffinity()
//
//        }
//        nodata.visibility = View.GONE
//        layoutDataView.visibility = View.VISIBLE
//        skelton2 = Skeleton.bind(rvNotification)
//                .adapter(adapter)
//                .load(R.layout.skelton_cart_item)
//                .shimmer(true)
//                .angle(30)
//                .count(6)
//                .show()
//      //  val progressBarHandler = ProgressBarHandler(this@CartItemActivity)
//      //  progressBarHandler.show()
//        val apiInterface = ApiClient.getRetrofitClientForHeader(this@NotificationActivity).create(ApiInterface::class.java)
//        apiInterface.getNotificationData().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Observer<NotificationItemList> {
//                    override fun onSubscribe(d: Disposable?) {
//
//                    }
//
//                    override fun onNext(notificationItemList: NotificationItemList) {
//                        try {
//                            skelton2?.hide()
//                            if (notificationItemList!!.meta.status) {
//                                notificationItem = notificationItemList.data
//                                adapter!!.update(notificationItem)
//                                if(notificationItemList.data.size!=0){
//                                    skelton2?.hide()
//                                    nodata.visibility = View.GONE
//                                    layoutDataView.visibility = View.VISIBLE
//                                }else{
//                                    skelton2?.hide()
//                                    nodata.visibility = View.VISIBLE
//                                    layoutDataView.visibility = View.GONE
//                                }
//                            }
//                        }catch ( e :Exception){
//                            e.printStackTrace()
//                        }
//
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        e!!.printStackTrace()
//                        skelton2?.hide()
//                        nodata.visibility = View.VISIBLE
//                    //    progressBarHandler.hide()
//                    }
//
//                    override fun onComplete() {
//                        skelton2?.hide()
//                  //      progressBarHandler.hide()
//                    }
//                })
//    }
//
//    override fun onRefreshEvent() {
//
//    }
//}