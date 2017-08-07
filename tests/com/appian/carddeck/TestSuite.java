/*
 *    File: TestSuite.java
 *  Author: Robert J. Orr
 */
package com.appian.carddeck;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

/**
 * jUnit test suite
 *
 * @author Robert Orr
 * @version 1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CardTest.class, DeckTest.class})
public class TestSuite { }
