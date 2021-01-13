package com.tabnine.binary.requests.statusBar;

import com.tabnine.binary.BinaryRequest;
import com.tabnine.binary.requests.selection.SetStateBinaryResponse;
import com.tabnine.general.StaticConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author VISTALL
 * @since 13/01/2021
 *
 * from kotlin
 */
public class StatusBarPromotionShownRequest implements BinaryRequest<SetStateBinaryResponse> {
    private final String myText;

    public StatusBarPromotionShownRequest(String text) {
        myText = text;
    }

    public String getText() {
        return myText;
    }

    @Override
    public Object serialize() {
        return Map.of("SetState", Map.of("state_type", Map.of("StatusShown", this)));
    }

    @Override
    public Class<SetStateBinaryResponse> response() {
        return SetStateBinaryResponse.class;
    }

    @Override
    public boolean validate(@NotNull SetStateBinaryResponse response) {
        return StaticConfig.SET_STATE_RESPONSE_RESULT_STRING.equals(response.getResult());
    }
}
