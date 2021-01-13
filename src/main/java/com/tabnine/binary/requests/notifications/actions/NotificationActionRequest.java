package com.tabnine.binary.requests.notifications.actions;

import com.google.gson.annotations.SerializedName;
import com.tabnine.binary.BinaryRequest;
import com.tabnine.binary.requests.EmptyResponse;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author VISTALL
 * @since 13/01/2021
 */
public class NotificationActionRequest implements BinaryRequest<EmptyResponse> {
    private String id;
    private String selected;
    private String message;
    @SerializedName("notification_type")
    private String notificationType;
    private List<String> actions;

    public NotificationActionRequest(String id, String selected, String message, String notificationType, List<String> actions) {
        this.id = id;
        this.selected = selected;
        this.message = message;
        this.notificationType = notificationType;
        this.actions = actions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
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
        NotificationActionRequest that = (NotificationActionRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(selected, that.selected) &&
                Objects.equals(message, that.message) &&
                Objects.equals(notificationType, that.notificationType) &&
                Objects.equals(actions, that.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, selected, message, notificationType, actions);
    }

    @Override
    public Class<EmptyResponse> response() {
        return EmptyResponse.class;
    }

    @Override
    public Object serialize() {
        return Map.of("NotificationAction", this);
    }
}
