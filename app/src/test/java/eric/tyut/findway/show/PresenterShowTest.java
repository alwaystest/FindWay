package eric.tyut.findway.show;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Mzz on 2016/5/20.
 */
public class PresenterShowTest {

    PresenterShow mPresenter;
    IViewShow mView;
    @Before
    public void setUp() throws Exception {
        mView = mock(ResultActivity.class);
        mPresenter = new PresenterShow(mView);
    }

    @Test
    public void testCalculate() throws Exception {
        mPresenter.calculate("太原","孝西");
        verify(mView).onLoadStart();
    }

    @Test
    public void testCalculate1() throws Exception {
        mPresenter.calculate("","");
        verify(mView,times(0)).onLoadStart();
    }
}