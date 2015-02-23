package org.nailedtothex.ejbxmltest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class MyBeanIT {
    static Context ctx;
    static MyBean myBean;

    @BeforeClass
    public static void before() throws Exception {
        Properties props = new Properties();
        props.setProperty("remote.connections", "default");
        props.setProperty("remote.connection.default.port", "8080");
        props.setProperty("remote.connection.default.host", "localhost");
        props.setProperty("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        props.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        props.setProperty("org.jboss.ejb.client.scoped.context", "true");
        ctx = new InitialContext(props);
        myBean = (MyBean) ctx.lookup("ejb:/ejbxmltest//MyBeanImpl!org.nailedtothex.ejbxmltest.MyBean");
    }

    @AfterClass
    public static void after() throws Exception {
        ctx.close();
    }

    @Test
    public void test() throws Exception {
        org.junit.Assert.assertEquals("Hello", myBean.hello());
    }
}
