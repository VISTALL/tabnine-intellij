package com.tabnine.statusBar;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.impl.status.EditorBasedWidget;
import com.intellij.openapi.wm.impl.status.TextPanel;
import com.intellij.util.Consumer;
import com.intellij.util.ui.UIUtil;
import com.tabnine.binary.BinaryRequestFacade;
import com.tabnine.binary.requests.config.ConfigRequest;
import com.tabnine.binary.requests.statusBar.ConfigOpenedFromStatusBarRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import static com.tabnine.general.StaticConfig.ICON_AND_NAME;
import static com.tabnine.general.StaticConfig.ICON_AND_NAME_DARK;

public class TabnineStatusBarWidget extends EditorBasedWidget implements CustomStatusBarWidget, com.intellij.openapi.wm.StatusBarWidget.WidgetPresentation {
    private final BinaryRequestFacade binaryRequestFacade;

    public TabnineStatusBarWidget(@NotNull Project project, BinaryRequestFacade binaryRequestFacade) {
        super(project);
        this.binaryRequestFacade = binaryRequestFacade;
    }

    @NotNull
    @Override
    public String ID() {
        return getClass().getName();
    }

    // Compatability implementation. DO NOT ADD @Override.
    public JComponent getComponent() {
        TextPanel.WithIconAndArrows component = new TextPanel.WithIconAndArrows();

        component.setIcon(UIUtil.isUnderDarcula() ? ICON_AND_NAME_DARK : ICON_AND_NAME);
        component.setToolTipText(getTooltipText());
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                Objects.requireNonNull(getClickConsumer()).consume(e);
            }
        });

        return component;
    }

    // Compatability implementation. DO NOT ADD @Override.
    @Nullable
    public WidgetPresentation getPresentation() {
        return this;
    }

    // Compatability implementation. DO NOT ADD @Override.
    @Nullable
    public String getTooltipText() {
        return "Tabnine (Click to open settings)";
    }

    // Compatability implementation. DO NOT ADD @Override.
    @Nullable
    public Consumer<MouseEvent> getClickConsumer() {
        return (e) -> {
            if (!e.isPopupTrigger() && MouseEvent.BUTTON1 == e.getButton()) {
                binaryRequestFacade.executeRequest(new ConfigRequest());
                binaryRequestFacade.executeRequest(new ConfigOpenedFromStatusBarRequest());
            }
        };
    }
}
