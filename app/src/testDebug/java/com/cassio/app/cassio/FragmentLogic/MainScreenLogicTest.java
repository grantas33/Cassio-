package com.cassio.app.cassio.FragmentLogic;

import android.content.Context;

import com.cassio.app.cassio.Tools.StubLogProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MainScreenLogicTest {
    @Mock
    private Context mockContext;
    private MainScreenLogic mainScreenLogic;

    @Before
    public void setUp() throws Exception {
        mainScreenLogic = new MainScreenLogic(mockContext, new StubLogProvider());
    }

    @After
    public void tearDown() throws Exception {
        mainScreenLogic = null;
    }

    @Test
    public void getTotalCalories() throws Exception {
        assertEquals(333, mainScreenLogic.getTotalCalories());
    }

    @Test
    public void getTotalCarbohydrates() throws Exception {
        assertEquals(82.7, mainScreenLogic.getTotalCarbohydrates(), 0.1);
    }

    @Test
    public void getTotalProtein() throws Exception {
        assertEquals(6.7, mainScreenLogic.getTotalProtein(), 0.1);
    }

    @Test
    public void getTotalFat() throws Exception {
        assertEquals(2.8, mainScreenLogic.getTotalFat(), 0.1);
    }

}