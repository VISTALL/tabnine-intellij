package com.tabnine.binary.requests.statusBar;

import com.google.gson.annotations.SerializedName;
import com.tabnine.binary.BinaryResponse;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

/**
 * @author VISTALL
 * @since 13/01/2021
 * <p>
 * from kotlin
 */
public class StatusBarPromotionBinaryResponse implements BinaryResponse {
    private String myId;
    private String myMessage;
    @Nullable
    private List<String> myActions;
    @Nullable
    @SerializedName("notification_type")
    private String myNotificationType;

    public StatusBarPromotionBinaryResponse(String id, String message, @Nullable List<String> actions, @Nullable String notificationType) {
        myId = id;
        myMessage = message;
        myActions = actions;
        myNotificationType = notificationType;
    }

    public String getId() {
        return myId;
    }

    public void setId(String id) {
        myId = id;
    }

    public String getMessage() {
        return myMessage;
    }

    public void setMessage(String message) {
        myMessage = message;
    }

    @Nullable
    public List<String> getActions() {
        return myActions;
    }

    public void setActions(@Nullable List<String> actions) {
        myActions = actions;
    }

    @Nullable
    public String getNotificationType() {
        return myNotificationType;
    }

    public void setNotificationType(@Nullable String notificationType) {
        myNotificationType = notificationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatusBarPromotionBinaryResponse that = (StatusBarPromotionBinaryResponse) o;
        return Objects.equals(myId, that.myId) &&
                Objects.equals(myMessage, that.myMessage) &&
                Objects.equals(myActions, that.myActions) &&
                Objects.equals(myNotificationType, that.myNotificationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myId, myMessage, myActions, myNotificationType);
    }
}
