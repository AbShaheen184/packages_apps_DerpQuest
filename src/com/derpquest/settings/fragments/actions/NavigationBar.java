/*
 * Copyright (C) 2020 DerpFest
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.derpquest.settings.fragments.actions;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import androidx.preference.*;

import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable;
import com.android.settingslib.search.SearchIndexable;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

import com.android.internal.logging.nano.MetricsProto;
import com.android.internal.util.derp.derpUtils;

import com.derp.support.preference.SystemSettingSwitchPreference;

import java.util.ArrayList;
import java.util.List;

@SearchIndexable
public class NavigationBar extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener, Indexable {

    private static final String LAYOUT_SETTINGS = "navbar_layout_views";
    private static final String NAVIGATION_BAR_INVERSE = "navbar_inverse_layout";
    private static final String PIXEL_NAV_ANIMATION = "pixel_nav_animation";

    private Preference mLayoutSettings;
    private SwitchPreference mSwapNavButtons;
    private SystemSettingSwitchPreference mPixelNavAnimation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.navigation_bar);

        final PreferenceScreen prefScreen = getPreferenceScreen();

        mLayoutSettings = findPreference(LAYOUT_SETTINGS);
        mSwapNavButtons = findPreference(NAVIGATION_BAR_INVERSE);
        mPixelNavAnimation = findPreference(PIXEL_NAV_ANIMATION);

        if (!derpUtils.isThemeEnabled("com.android.internal.systemui.navbar.threebutton")) {
            prefScreen.removePreference(mLayoutSettings);
            prefScreen.removePreference(mSwapNavButtons);
            prefScreen.removePreference(mPixelNavAnimation);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.DERP;
    }

    public static final SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
        new BaseSearchIndexProvider() {
            @Override
            public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                    boolean enabled) {
                final ArrayList<SearchIndexableResource> result = new ArrayList<>();
                final SearchIndexableResource sir = new SearchIndexableResource(context);
                sir.xmlResId = R.xml.navigation_bar;
                result.add(sir);
                return result;
            }

            @Override
            public List<String> getNonIndexableKeys(Context context) {
                final List<String> keys = super.getNonIndexableKeys(context);
                return keys;
            }
    };
}
