package com.aceegg.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
<<<<<<< HEAD
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
=======
@RunWith(AndroidJUnit4.class) public class ExampleInstrumentedTest {
    @Test public void useAppContext() throws Exception {
>>>>>>> e535e08044465be81e540d7a4dd50100d9a59be6
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.aceegg.data.test", appContext.getPackageName());
    }
}
