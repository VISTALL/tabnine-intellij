package com.tabnine.binary.requests.notifications.shown;

import com.tabnine.binary.BinaryRequest;
import com.tabnine.binary.requests.selection.SetStateBinaryResponse;
import com.tabnine.general.StaticConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

/**
 * @author VISTALL
 * @since 13/01/2021
 */
public class NotificationShownRequest implements BinaryRequest<SetStateBinaryResponse> {
    private String text;

    public NotificationShownRequest(String text) {
        this.text = text;
    }

    @Override
    public Class<SetStateBinaryResponse> response() {
        return SetStateBinaryResponse.class;
    }

    @Override
    public Object serialize() {
        return Map.of("SetState", Map.of("state_type", Map.of("NotificationShown", this)));
    }

    @Override
    public boolean validate(@NotNull SetStateBinaryResponse response) {
        return StaticConfig.SET_STATE_RESPONSE_RESULT_STRING.equals(response.getResult());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotificationShownRequest that = (NotificationShownRequest) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
