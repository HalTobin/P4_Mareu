package com.example.maru;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.maru.ui.activity.ListMeetingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListTest {

    private ListMeetingActivity mActivity;
    private ActivityScenario mScenario;

    @Rule
    public ActivityScenarioRule<ListMeetingActivity> mActivityRule =
            new ActivityScenarioRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        Intents.init();
        mScenario = mActivityRule.getScenario();
        assertThat(mScenario, notNullValue());
    }

    @Test
    public void openAddMeeting() {

    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.maru", appContext.getPackageName());
    }
}