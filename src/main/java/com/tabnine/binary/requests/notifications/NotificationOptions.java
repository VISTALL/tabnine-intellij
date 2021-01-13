package com.tabnine.binary.requests.notifications;

import java.util.List;
import java.util.Objects;

/**
 * @author VISTALL
 * @since 13/01/2021
 */
public class NotificationOptions {
    private String key;
    private List<String> actions;

    public NotificationOptions(String key, List<String> actions) {
        this.key = key;
        this.actions = actions;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotificationOptions that = (NotificationOptions) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, actions);
    }
}
