package com.tabnine.binary.requests.notifications;

import com.tabnine.binary.BinaryResponse;

import java.util.List;
import java.util.Objects;

/**
 * @author VISTALL
 * @since 13/01/2021
 */
public class NotificationsBinaryResponse implements BinaryResponse  {
    private List<BinaryNotification> notifications;

    public List<BinaryNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<BinaryNotification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotificationsBinaryResponse that = (NotificationsBinaryResponse) o;
        return Objects.equals(notifications, that.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notifications);
    }
}
