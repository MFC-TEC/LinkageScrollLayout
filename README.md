# LinkageScrollLayout
A container which supports any two view scrolling in it.

It's easy to use and powerful, as it support any two child view in it.

Support 'API LEVEL >= 19'

[Download APK](https://github.com/baiduapp-tec/LinkageScrollLayout/tree/master/app-debug.apk)

* Top is NA, Bottom is H5
    <div class='row'>
        <img src='http://srain-github.qiniudn.com/ultra-ptr/store-house-string-array.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
        <img src='http://srain-github.qiniudn.com/ultra-ptr/store-house-string.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

* Top is H5, Bottom is NA
    <div class='row'>
        <img src='http://srain-github.qiniudn.com/ultra-ptr/store-house-string-array.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
        <img src='http://srain-github.qiniudn.com/ultra-ptr/store-house-string.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
    </div>

* Top is NA, Bottom is NA
    <div class='row'>
        <img src='http://srain-github.qiniudn.com/ultra-ptr/store-house-string-array.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
        <img src='http://srain-github.qiniudn.com/ultra-ptr/store-house-string.gif' width="300px" style='border: #f1f1f1 solid 1px'/>
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

you can user LinkageScrollListener to listen LinkageScroll Event<br><br>.

example:
```java
mLinkageLayout = findViewById(R.id.root);
// ...
// ...
mLinkageLayout.addLinkageScrollListener(new LinkageScrollListener() {
    @Override
    public void onTopJustIn(PosIndicator posIndicator) {
        // when top view move into layout, this function will be called
    }

    @Override
    public void onTopJustOut(PosIndicator posIndicator) {
        // when top view move out of layout, this function will be called
    }

    @Override
    public void onBottomJustIn(PosIndicator posIndicator) {
        // when bottom view move into layout, this function will be called
    }

    @Override
    public void onBottomJustOut(PosIndicator posIndicator) {
        // when bottom view move out of layout, this function will be called
    }
});
```

# Contact & Help
Please feel free to contact me if there is any proble when using LinkageScrollLayout
* email: zhanghao43@baidu.com
* blog: https://blog.csdn.net/h_zhang


