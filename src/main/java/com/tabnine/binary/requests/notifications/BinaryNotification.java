package com.tabnine.binary.requests.notifications;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

/**
 * @author VISTALL
 * @since 13/01/2021
 */
public class BinaryNotification {
    private String id;
    private String message;
    private List<NotificationOptions> options;
    @SerializedName("notification_type")
    private String notificationType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NotificationOptions> getOptions() {
        return options;
    }

    public void setOptions(List<NotificationOptions> options) {
        this.options = options;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryNotification that = (BinaryNotification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message) &&
                Objects.equals(options, that.options) &&
                Objects.equals(notificationType, that.notificationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, options, notificationType);
    }
}
