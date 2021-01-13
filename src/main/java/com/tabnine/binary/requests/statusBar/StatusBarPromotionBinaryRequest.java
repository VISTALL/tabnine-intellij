package com.tabnine.binary.requests.statusBar;

import com.tabnine.binary.BinaryRequest;
import com.tabnine.binary.exceptions.TabNineInvalidResponseException;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author VISTALL
 * @since 13/01/2021
 */
public class StatusBarPromotionBinaryRequest implements BinaryRequest<StatusBarPromotionBinaryResponse> {
    @Override
    public Class<StatusBarPromotionBinaryResponse> response() {
        return StatusBarPromotionBinaryResponse.class;
    }

    @Override
    public Object serialize() {
        return Map.of("StatusBar", Map.of());
    }

    @Override
    public boolean shouldBeAllowed(@NotNull TabNineInvalidResponseException e) {
        // If there is no promotion to show, the binary returns "null" as response, so we just ignore it.
        return e.getRawResponse().filter(it -> it.equals("null")).isPresent();
    }
}
