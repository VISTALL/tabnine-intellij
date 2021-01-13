package com.tabnine.binary.requests.notifications;

import com.tabnine.binary.BinaryRequest;

import java.util.Map;

/**
 * @author VISTALL
 * @since 13/01/2021
 *
 * from kotlin
 */
public class NotificationsBinaryRequest implements BinaryRequest<NotificationsBinaryResponse> {
    @Override
    public Class<NotificationsBinaryResponse> response() {
        return NotificationsBinaryResponse.class;
    }

    @Override
    public Object serialize() {
        return Map.of("Notifications", Map.of());
    }
}
