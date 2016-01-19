# SwitchViewGroup
Android中一个垂直运动的广告条,运用动画层来达到不会卡顿的效果,仅供参考原理，实际运用还请使用Android原生控件SwitchView

#Usage
```
 <!--在这里加上广告小喇叭-->
    <com.github.switchviewgroup.views.SwitchViewGroup
        android:id="@+id/switchViewGroup"
        android:layout_width="@dimen/vertical_view_group_width"
        android:layout_height="@dimen/vertical_view_group_height"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true"
        android:paddingLeft="10dp"
        android:visibility="visible" />
```

```
 for (int i = 0; i < 3; i++) {
            datas.add("trumpet____" + i);
        }
        mSwitchViewGroup.addData(datas);
        mSwitchViewGroup.startScroll();
        mSwitchViewGroup.setOnClickTabListener(new SwitchViewGroup.OnClickTabListener() {
            @Override
            public void onClickTab(int index) {
                Snackbar.make(mSwitchViewGroup, datas.get(index), Snackbar.LENGTH_SHORT).show();
            }
        });
```

##效果图
![SwtichView](http://7xq7wz.com1.z0.glb.clouddn.com/SwitchView.gif)

License
=======

    Copyright xuyunqiang 1539140295@qq.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
