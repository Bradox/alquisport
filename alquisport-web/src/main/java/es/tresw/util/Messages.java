package es.tresw.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    // property file is: package/name/messages.properties
    private static final String EMAIL_BUNDLE_NAME = "email";
    private static final ResourceBundle EMAIL_RESOURCE_BUNDLE = ResourceBundle.getBundle(EMAIL_BUNDLE_NAME);
    private static final String WEB_BUNDLE_NAME = "ValidationMessages";
    private static final ResourceBundle WEB_RESOURCE_BUNDLE = ResourceBundle.getBundle(WEB_BUNDLE_NAME);
    
    public static String getStringEmail(String key) {
        try {
            return EMAIL_RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
        	return "???" + key + "???";
        }
    }
    public static String getStringEmail(String key, Object... params  ) {
        try {
            return MessageFormat.format(EMAIL_RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
        	return "???" + key + "???";
        }
    }
    
    public static String getString(String key) {
        try {
            return WEB_RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
        	return "???" + key + "???";
        }
    }
    public static String getString(String key, Object... params  ) {
        try {
            return MessageFormat.format(WEB_RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
            return "???" + key + "???";
        }
    }
}