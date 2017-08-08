/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.IdentityAndAudit;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author krisnamourtscf
 */
@Entity
@Table(name = "proxy")
public class Proxy extends IdentityAndAudit {

    private String server;
    private String ip;
    private String port;

    public Proxy() {
    }

    public void configure() {
        System.setProperty("http.proxyHost", getIp());
        System.setProperty("http.proxyPort", getPort());
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

}
