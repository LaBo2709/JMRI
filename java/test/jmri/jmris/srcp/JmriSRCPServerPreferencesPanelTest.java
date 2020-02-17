package jmri.jmris.srcp;

import jmri.util.JUnitUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests for the jmri.jmris.srcp.JmriSRCPServerPreferencesPanel class
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class JmriSRCPServerPreferencesPanelTest {

    @Test public void testCtor() {
        JmriSRCPServerPreferencesPanel a = new JmriSRCPServerPreferencesPanel();
        Assert.assertNotNull(a);
    }

    @BeforeEach public void setUp() {
        JUnitUtil.setUp();
        jmri.util.JUnitUtil.resetProfileManager();
    }

    @AfterEach public void tearDown() throws Exception {
        JUnitUtil.tearDown();
    }

}
