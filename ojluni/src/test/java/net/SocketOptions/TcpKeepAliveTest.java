/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 8194298
 * @summary Add support for per Socket configuration of TCP keepalive
 * @modules jdk.net
 * @run main TcpKeepAliveTest
 */
package test.java.net.SocketOptions;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import jdk.net.ExtendedSocketOptions;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TcpKeepAliveTest {

    private static final String LOCAL_HOST = "127.0.0.1";
    private static final int DEFAULT_KEEP_ALIVE_PROBES = 7;
    private static final int DEFAULT_KEEP_ALIVE_TIME = 1973;
    private static final int DEFAULT_KEEP_ALIVE_INTVL = 53;

    @Test
    public void testTcpKeepAlive() throws IOException {

        try (ServerSocket ss = new ServerSocket(0);
             Socket s = new Socket(LOCAL_HOST, ss.getLocalPort());
             DatagramSocket ds = new DatagramSocket(0);
             MulticastSocket mc = new MulticastSocket(0)) {
            if (ss.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPIDLE)) {
                ss.setOption(ExtendedSocketOptions.TCP_KEEPIDLE, DEFAULT_KEEP_ALIVE_TIME);
                if (ss.getOption(ExtendedSocketOptions.TCP_KEEPIDLE) != DEFAULT_KEEP_ALIVE_TIME) {
                    Assert.fail("Test failed, TCP_KEEPIDLE should have been " + DEFAULT_KEEP_ALIVE_TIME);
                }
            }
            if (ss.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPCOUNT)) {
                ss.setOption(ExtendedSocketOptions.TCP_KEEPCOUNT, DEFAULT_KEEP_ALIVE_PROBES);
                if (ss.getOption(ExtendedSocketOptions.TCP_KEEPCOUNT) != DEFAULT_KEEP_ALIVE_PROBES) {
                    Assert.fail("Test failed, TCP_KEEPCOUNT should have been " + DEFAULT_KEEP_ALIVE_PROBES);
                }
            }
            if (ss.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPINTERVAL)) {
                ss.setOption(ExtendedSocketOptions.TCP_KEEPINTERVAL, DEFAULT_KEEP_ALIVE_INTVL);
                if (ss.getOption(ExtendedSocketOptions.TCP_KEEPINTERVAL) != DEFAULT_KEEP_ALIVE_INTVL) {
                    Assert.fail("Test failed, TCP_KEEPINTERVAL should have been " + DEFAULT_KEEP_ALIVE_INTVL);
                }
            }
            if (s.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPIDLE)) {
                s.setOption(ExtendedSocketOptions.TCP_KEEPIDLE, DEFAULT_KEEP_ALIVE_TIME);
                if (s.getOption(ExtendedSocketOptions.TCP_KEEPIDLE) != DEFAULT_KEEP_ALIVE_TIME) {
                    Assert.fail("Test failed, TCP_KEEPIDLE should have been " + DEFAULT_KEEP_ALIVE_TIME);
                }
            }
            if (s.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPCOUNT)) {
                s.setOption(ExtendedSocketOptions.TCP_KEEPCOUNT, DEFAULT_KEEP_ALIVE_PROBES);
                if (s.getOption(ExtendedSocketOptions.TCP_KEEPCOUNT) != DEFAULT_KEEP_ALIVE_PROBES) {
                    Assert.fail("Test failed, TCP_KEEPCOUNT should have been " + DEFAULT_KEEP_ALIVE_PROBES);
                }
            }
            if (s.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPINTERVAL)) {
                s.setOption(ExtendedSocketOptions.TCP_KEEPINTERVAL, DEFAULT_KEEP_ALIVE_INTVL);
                if (s.getOption(ExtendedSocketOptions.TCP_KEEPINTERVAL) != DEFAULT_KEEP_ALIVE_INTVL) {
                    Assert.fail("Test failed, TCP_KEEPINTERVAL should have been " + DEFAULT_KEEP_ALIVE_INTVL);
                }
            }
            if (ds.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPCOUNT)) {
                Assert.fail("Test failed, TCP_KEEPCOUNT is applicable"
                        + " for TCP Sockets only.");
            }
            if (ds.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPIDLE)) {
                Assert.fail("Test failed, TCP_KEEPIDLE is applicable"
                        + " for TCP Sockets only.");
            }
            if (ds.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPINTERVAL)) {
                Assert.fail("Test failed, TCP_KEEPINTERVAL is applicable"
                        + " for TCP Sockets only.");
            }
            if (mc.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPCOUNT)) {
                Assert.fail("Test failed, TCP_KEEPCOUNT is applicable"
                        + " for TCP Sockets only");
            }
            if (mc.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPIDLE)) {
                Assert.fail("Test failed, TCP_KEEPIDLE is applicable"
                        + " for TCP Sockets only");
            }
            if (mc.supportedOptions().contains(ExtendedSocketOptions.TCP_KEEPINTERVAL)) {
                Assert.fail("Test failed, TCP_KEEPINTERVAL is applicable"
                        + " for TCP Sockets only");
            }
        }
    }
}