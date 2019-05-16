package org.andy.beans.powermock.enumdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * ==========================================================================
 * PersonTypeTest
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 18:20
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PersonType.class})
public class PersonTypeTest {
    @Test
    public void testMockEnum(){
        PersonType personType = PowerMockito.mock(PersonType.class);
        Whitebox.setInternalState(PersonType.class, "N", personType);
        when(personType.getType()).thenReturn("mockN");
        assertThat(PersonType.N.getType(), is("mockN"));
        assertThat(PersonType.S.getType(), is("student"));
    }
}