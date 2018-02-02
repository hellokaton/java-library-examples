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

package io.github.biezhi.jetty.websocket.jsr;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@ServerEndpoint("/time/")
public class TimeSocket implements Runnable {
    private TimeZone timezone;
    private Session  session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        this.timezone = TimeZone.getTimeZone("UTC");
        new Thread(this).start();
    }

    @OnClose
    public void onClose(CloseReason close) {
        this.session = null;
    }

    @Override
    public void run() {
        while (this.session != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                dateFormat.setTimeZone(timezone);

                String timestamp = dateFormat.format(new Date());
                this.session.getBasicRemote().sendText(timestamp);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
