package eric.tyut.findway.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Mzz on 2016/5/19.
 */
public class PresenterMainTest {
    PresenterMain mPresenterMain;
    IViewMain mView;
    ModelMain mModel;

    @Before
    public void setUp() throws Exception {
        mView = mock(MainActivity.class);
        mModel = mock(ModelMain.class);
        mPresenterMain = new PresenterMain(mView, mModel);
    }

    @Test
    public void testOnClickSearch() throws Exception {
        mPresenterMain.onClickSearch();
        verify(mView).onLoadStart();
    }

    @Test
    public void testOnClickSearch1() throws Exception {
        mPresenterMain.onClickSearch();
        verify(mView).getFrom();
    }

    @Test
    public void testOnClickSearch2() throws Exception {
        mPresenterMain.onClickSearch();
        verify(mView).getTo();
    }
}