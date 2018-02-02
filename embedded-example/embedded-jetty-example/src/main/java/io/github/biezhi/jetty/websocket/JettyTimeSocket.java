//
//  ========================================================================
//  Copyright (c) 1995-2015 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package io.github.biezhi.jetty.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@WebSocket
public class JettyTimeSocket implements Runnable {
    private TimeZone timezone;
    private Session  session;

    @OnWebSocketConnect
    public void onOpen(Session session) {
        this.session = session;
        this.timezone = TimeZone.getTimeZone("UTC");
        new Thread(this).start();
    }

    @OnWebSocketClose
    public void onClose(int closeCode, String closeReasonPhrase) {
        this.session = null;
    }

    @Override
    public void run() {
        while (this.session != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                dateFormat.setTimeZone(timezone);

                String timestamp = dateFormat.format(new Date());
                this.session.getRemote().sendString(timestamp);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
