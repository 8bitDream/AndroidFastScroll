/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zhanghai.android.fastscroll;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.util.Consumer;

public class PopupStyles {

    private PopupStyles() {}

    public static Consumer<TextView> DEFAULT = popupView -> {
        Resources resources = popupView.getResources();
        int minimumSize = resources.getDimensionPixelSize(R.dimen.afs_popup_min_size);
        popupView.setMinimumWidth(minimumSize);
        popupView.setMinimumHeight(minimumSize);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)
                popupView.getLayoutParams();
        layoutParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginEnd(resources.getDimensionPixelOffset(R.dimen.afs_popup_margin_end));
        } else {
            layoutParams.setMargins(layoutParams.leftMargin,
                    layoutParams.topMargin,
                    resources.getDimensionPixelOffset(R.dimen.afs_popup_margin_end),
                    layoutParams.bottomMargin);

        }
        popupView.setLayoutParams(layoutParams);
        Context context = popupView.getContext();
        popupView.setBackground(new AutoMirrorDrawable(Utils.getGradientDrawableWithTintAttr(
                R.drawable.afs_popup_background, R.attr.colorControlActivated, context)));
        popupView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        popupView.setGravity(Gravity.CENTER);
        popupView.setIncludeFontPadding(false);
        popupView.setSingleLine(true);
        popupView.setTextColor(Utils.getColorFromAttrRes(android.R.attr.textColorPrimaryInverse,
                context));
        popupView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimensionPixelSize(
                R.dimen.afs_popup_text_size));
    };

    public static Consumer<TextView> MD2 = popupView -> {
        Resources resources = popupView.getResources();
        popupView.setMinimumWidth(resources.getDimensionPixelSize(
                R.dimen.afs_md2_popup_min_width));
        popupView.setMinimumHeight(resources.getDimensionPixelSize(
                R.dimen.afs_md2_popup_min_height));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)
                popupView.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.setMarginEnd(resources.getDimensionPixelOffset(
                    R.dimen.afs_md2_popup_margin_end));
        } else {
            layoutParams.setMargins(layoutParams.leftMargin,
                    layoutParams.topMargin,
                    resources.getDimensionPixelOffset(R.dimen.afs_md2_popup_margin_end),
                    layoutParams.bottomMargin);
        }
        popupView.setLayoutParams(layoutParams);
        Context context = popupView.getContext();
        popupView.setBackground(new Md2PopupBackground(context));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupView.setElevation(resources.getDimensionPixelOffset(R.dimen.afs_md2_popup_elevation));
        }
        popupView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        popupView.setGravity(Gravity.CENTER);
        popupView.setIncludeFontPadding(false);
        popupView.setSingleLine(true);
        popupView.setTextColor(Utils.getColorFromAttrRes(android.R.attr.textColorPrimaryInverse,
                context));
        popupView.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimensionPixelSize(
                R.dimen.afs_md2_popup_text_size));
    };
}
