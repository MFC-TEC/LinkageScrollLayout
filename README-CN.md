# LinkageScrollLayout

LinkageScrollLayout 是一个联动容器，目的实现两个子view联动scroll效果.

LinkageScrollLayout易用且强大，它可以支持任意类型的子view.

最低api要求 'API LEVEL >= 19'

[下载Demo](https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/lsl-debug.apk)

### Top is H5, Bottom is NA

* WebView & RecyclerView
    <div class='row'>
        <img src='https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/gif/H5-NA-WVRV.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

* WebView & ListView
    <div class='row'>
        <img src='https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/gif/H5-NA-WVLV.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

* WebView & GridView
    <div class='row'>
        <img src='https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/gif/H5-NA-WVGV.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

* WebView & ScrollView
    <div class='row'>
        <img src='https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/gif/H5-NA-WVSV.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

### Top is NA, Bottom is H5
* RecyclerView & WebView
    <div class='row'>
        <img src='https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/gif/NA-H5-RVWV.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

## Tos is NA, Bottom is NA

* RecyclerView & ScrollView
    <div class='row'>
        <img src='https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/gif/NA-NA-RVSV.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

* ListView & RecyclerView
    <div class='row'>
        <img src='https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/gif/NA-NA-LVRV.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

* GridView & ReyclerView
    <div class='row'>
        <img src='https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/gif/NA-NA-GVRV.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

* ScrollView & GridView
    <div class='row'>
        <img src='https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/gif/NA-NA-SVGV.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>
    
# 使用方法
#### in xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.lorien.linkagescroll.LinkageScrollLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".activity.WVLVActivity">

    <com.lorien.linkagescroll.view.LWebView
        android:id="@+id/webview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never"
        android:scrollbars="none"/>

    <com.lorien.linkagescroll.view.LListView
        android:id="@+id/listview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never"
        android:scrollbars="none"/>

</com.lorien.linkagescroll.LinkageScrollLayout>
```

* 必须使用LinkageScrollLayout作为你Activity or Fragment的根布局.
* 目前仅支持2个子view的联动滚动.
* 复写了WebView, ListView, ScrollView, RecyclerView, GridView...完成了联动scroll的必要功能.当然你也可以参考项目中的LWebView, LListView...去复写自己相应控件

#### UI 接口

* 你可以使用 LinkageScrollListener 去监听联动scroll容器的滚动事件.
* LinkageScrollListenerAdapter为LinkageScrollListener的空实现，如果业务Listener仅仅关心其中的某些事件，可以直接使用LinkageScrollListenerAdapter，而不用去实现接口LinkageScrollListener中的所有方法。

示例:
```java
mLinkageLayout.addLinkageScrollListener(new LinkageScrollListenerAdapter() {
    @Override
    public void onTopJustIn(PosIndicator posIndicator) {
        // when top view move into layout, this function will be called
        Log.d(TAG, "onTopJustIn");
    }

    @Override
    public void onTopJustOut(PosIndicator posIndicator) {
        // when top view move out of layout, this function will be called
        Log.d(TAG, "onTopJustOut");
    }

    @Override
    public void onBottomJustIn(PosIndicator posIndicator) {
        // when bottom view move into layout, this function will be called
        Log.d(TAG, "onBottomJustIn");
    }

    @Override
    public void onBottomJustOut(PosIndicator posIndicator) {
        // when bottom view move out of layout, this function will be called
        Log.d(TAG, "onBottomJustOut");
    }

    @Override
    public void onPositionChanged(PosIndicator posIndicator) {
        // when The position of TopView and BottomView has changed, this function will be called
        Log.d(TAG, "onPositionChanged, postion: " + posIndicator.getCurrentPos());
    }
});
```

# 联系方式 
如果你在使用LinkageScrollLayout过程中发现任何问题，你可以通过如下方式联系我：
* 邮箱: zhanghao43@baidu.com
* 微博：https://weibo.com/u/5894400455


