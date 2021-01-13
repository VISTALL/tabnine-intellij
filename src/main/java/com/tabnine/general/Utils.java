package com.tabnine.general;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import consulo.container.plugin.PluginDescriptor;
import consulo.container.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public final class Utils {
    private static final String UNKNOWN = "Unknown";

    public static String getTabNinePluginVersion() {
        return getTabNinePluginDescriptor().map(PluginDescriptor::getVersion).orElse(UNKNOWN);
    }

    @NotNull
    public static Optional<PluginDescriptor> getTabNinePluginDescriptor() {
        return Optional.of(PluginManager.getPlugin(Utils.class));
    }

    public static boolean endsWithADot(Document doc, int positionBeforeSuggestionPrefix) {
        int begin = positionBeforeSuggestionPrefix - ".".length();
        if (begin < 0 || positionBeforeSuggestionPrefix > doc.getTextLength()) {
            return false;
        } else {
            String tail = doc.getText(new TextRange(begin, positionBeforeSuggestionPrefix));
            return tail.equals(".");
        }
    }

    @NotNull
    public static String readContent(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString(StandardCharsets.UTF_8.name()).trim();
    }

    @NotNull
    public static Integer toInt(@Nullable Long aLong) {
        if(aLong == null) {
            return 0;
        }

        return Math.toIntExact(aLong);
    }

    public static String cmdSanitize(String text) {
        return text.replace(" ", "");
    }
}
