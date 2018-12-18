package com.example.milan.rftproject;

import android.view.View;
import android.widget.RelativeLayout;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class LoginFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule=new  ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity=null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        RelativeLayout rcontainer=(RelativeLayout) mainActivity.findViewById(R.id.fragment_container);
        assertNotNull(rcontainer);

        LoginFragmentTest fragment=new LoginFragmentTest();

        mainActivity.getFragmentManager().beginTransaction().add(rcontainer.getId(),fragment).commitAllowingStateLoss();

        getInstrumentation().waitForIdleSync();

        View view= fragment.getView.findViewById(R.id.fragment_container);

        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {

        mainActivity=null;
    }
}