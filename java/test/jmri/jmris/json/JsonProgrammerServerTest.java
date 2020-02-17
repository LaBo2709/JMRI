package jmri.jmris.json;

import org.junit.Assert;
import jmri.server.json.JsonMockConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;

/**
 *
 * @author Paul Bender Copyright (C) 2017	
 */
public class JsonProgrammerServerTest {

    @Test
    public void testCTor() {
        JsonProgrammerServer t = new JsonProgrammerServer(new JsonMockConnection((DataOutputStream) null));
        Assert.assertNotNull("exists",t);
    }

    // The minimal setup for log4J
    @BeforeEach
    public void setUp() {
        jmri.util.JUnitUtil.setUp();
        jmri.util.JUnitUtil.resetProfileManager();
    }

    @AfterEach
    public void tearDown() {
        jmri.util.JUnitUtil.tearDown();
    }

}
