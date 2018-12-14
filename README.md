#### [中文版文档](https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/README-CN.md)

# LinkageScrollLayout

A container which supports two view scrolling in it.

It's easy to use and powerful, as it support any two child view in it.

Support 'API LEVEL >= 19'

[Download APK](https://github.com/baiduapp-tec/LinkageScrollLayout/blob/master/lsl-debug.apk)

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
    
# Usage
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

* you must use LinkageScrollLayout as you content layout in your activity or fragment.
* LinkageScrollLayout only support two child to scroll.
* I have override WebView, ListView, GridView, RecyclerView and ScrollView to provide necessary function which LinkageScrollLayout require. So you must use LWebView instead of WebView, LListView instead of ListView...
* If you want to add other views into LinkageScrollLayout, you can refer to LWebView, LListView, LRecyclerView and so on.

#### UI Interface

you can use LinkageScrollListener to listen LinkageScroll Event.<br><br>

example:
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

# Contact & Help
Please feel free to contact me if there is any proble when using LinkageScrollLayout
* email: zhanghao43@baidu.com
* weibo: https://weibo.com/u/5894400455


