package com.chequer.ax5.api.demo.utils;

import eu.bitwalker.useragentutils.*;

import javax.servlet.http.HttpServletRequest;

public class AgentUtils {

    public static String getUserAgentString(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static UserAgent getUserAgent(HttpServletRequest request) {
        try {
            String userAgentString = getUserAgentString(request);
            return UserAgent.parseUserAgentString(userAgentString);
        } catch (Exception e) {
            // ignored
        }
        return null;
    }

    public static OperatingSystem getUserOs(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        return userAgent == null ? OperatingSystem.UNKNOWN : userAgent.getOperatingSystem();
    }

    public static Browser getBrowser(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        return userAgent == null ? Browser.UNKNOWN : userAgent.getBrowser();
    }

    public static Version getBrowserVersion(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        return userAgent == null ? new Version("0", "0", "0") : userAgent.getBrowserVersion();
    }

    public static BrowserType getBrowserType(HttpServletRequest request) {
        Browser browser = getBrowser(request);
        return browser == null ? BrowserType.UNKNOWN : browser.getBrowserType();
    }

    public static RenderingEngine getRenderingEngine(HttpServletRequest request) {
        Browser browser = getBrowser(request);
        return browser == null ? RenderingEngine.OTHER : browser.getRenderingEngine();
    }

    public static DeviceType getDeviceType(HttpServletRequest request) {
        OperatingSystem operatingSystem = getUserOs(request);
        return operatingSystem == null ? DeviceType.UNKNOWN : operatingSystem.getDeviceType();
    }

    public static Manufacturer getManufacturer(HttpServletRequest request) {
        OperatingSystem operatingSystem = getUserOs(request);
        return operatingSystem == null ? Manufacturer.OTHER : operatingSystem.getManufacturer();
    }
}
